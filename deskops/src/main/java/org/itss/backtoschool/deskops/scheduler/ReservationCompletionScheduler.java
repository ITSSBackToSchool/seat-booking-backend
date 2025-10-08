package org.itss.backtoschool.deskops.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Scheduled task to automatically mark past reservations as COMPLETED.
 * Runs daily at 00:05 AM to process reservations from previous days.
 * Also handles time-based reservations that have ended during the current day.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCompletionScheduler {

    private final ReservationRepository reservationRepository;

    /**
     * Completes all ACTIVE reservations that have ended.
     * Runs every day at 00:05 AM (cron: second minute hour day month weekday)
     * Completion criteria:
     * 1. All reservations from past dates (before today)
     * 2. Today's time-slot reservations where endTime has passed
     */
    @Scheduled(cron = "0 5 0 * * ?")
    @Transactional
    public void completeReservations() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        log.info("Starting scheduled reservation completion check at {}", now);

        // 1. Find all ACTIVE reservations from past dates (not including today)
        List<Reservation> pastReservations = reservationRepository
                .findAllByReservationDateLessThanAndStatus(today, ReservationStatus.ACTIVE);

        List<Reservation> allToComplete = new ArrayList<>(pastReservations);
        log.debug("Found {} past date reservations to complete", pastReservations.size());

        // 2. Find today's reservations and check if time-slot ones have ended
        List<Reservation> todaysReservations = reservationRepository
                .findAllByReservationDateAndStatus(today, ReservationStatus.ACTIVE);

        List<Reservation> endedTodayReservations = todaysReservations.stream()
                .filter(r -> r.getEndTime() != null)  // Only time-slot reservations
                .filter(r -> r.getEndTime().isBefore(now) || r.getEndTime().equals(now))  // End time has passed
                .toList();

        allToComplete.addAll(endedTodayReservations);
        log.debug("Found {} ended time-slot reservations for today", endedTodayReservations.size());

        if (allToComplete.isEmpty()) {
            log.info("No reservations to complete - all reservations are already processed");
            return;
        }

        log.info("Found total of {} ACTIVE reservations to mark as COMPLETED", allToComplete.size());

        // Update all reservations to COMPLETED status
        allToComplete.forEach(reservation -> {
            String timeInfo = reservation.getEndTime() != null
                    ? String.format(" (time: %s-%s)", reservation.getStartTime(), reservation.getEndTime())
                    : " (all-day)";

            log.debug("Marking reservation {} (date: {}{}, seat: {}) as COMPLETED",
                    reservation.getId(),
                    reservation.getReservationDate(),
                    timeInfo,
                    reservation.getSeat().getId());

            reservation.setStatus(ReservationStatus.COMPLETED);
        });

        // Bulk save all updated reservations in a single transaction
        reservationRepository.saveAll(allToComplete);

        log.info("Successfully marked {} reservations as COMPLETED", allToComplete.size());
    }
}
