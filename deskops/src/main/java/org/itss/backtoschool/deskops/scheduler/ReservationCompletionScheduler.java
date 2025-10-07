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
import java.util.List;

/**
 * Scheduled task to automatically mark past reservations as COMPLETED.
 * Runs daily at 00:05 AM to process reservations from previous days.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCompletionScheduler {

    private final ReservationRepository reservationRepository;

    /**
     * Completes all ACTIVE reservations from past dates.
     * Runs every day at 00:05 AM (cron: second minute hour day month weekday)
     */
    @Scheduled(cron = "0 5 0 * * ?")
    @Transactional
    public void completeReservations() {
        LocalDate today = LocalDate.now();

        log.info("Starting scheduled reservation completion check for dates before {}", today);

        // Find all ACTIVE reservations from past dates (not including today)
        List<Reservation> toComplete = reservationRepository
                .findAllByReservationDateLessThanAndStatus(today, ReservationStatus.ACTIVE);

        if (toComplete.isEmpty()) {
            log.info("No reservations to complete - all past reservations are already processed");
            return;
        }

        log.info("Found {} ACTIVE reservations to mark as COMPLETED", toComplete.size());

        // Update all reservations to COMPLETED status
        toComplete.forEach(reservation -> {
            log.debug("Marking reservation {} (date: {}, seat: {}) as COMPLETED",
                    reservation.getId(),
                    reservation.getReservationDate(),
                    reservation.getSeat().getId());
            reservation.setStatus(ReservationStatus.COMPLETED);
        });

        // Bulk save all updated reservations in a single transaction
        reservationRepository.saveAll(toComplete);

        log.info("Successfully marked {} reservations as COMPLETED", toComplete.size());
    }
}
