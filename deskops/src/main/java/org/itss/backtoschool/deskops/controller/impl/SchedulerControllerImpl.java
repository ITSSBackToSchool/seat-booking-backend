package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.controller.SchedulerController;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.scheduler.ReservationCompletionScheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SchedulerControllerImpl implements SchedulerController {

    private final ReservationCompletionScheduler scheduler;
    private final ReservationRepository reservationRepository;

    @Override
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public ResponseEntity<String> triggerReservationCompletion() {
        log.info("Manual trigger of reservation completion job requested");

        // Get count before execution
        LocalDate today = LocalDate.now();
        List<Reservation> toComplete = reservationRepository
                .findAllByReservationDateLessThanAndStatus(today, ReservationStatus.ACTIVE);
        int count = toComplete.size();

        // Execute the scheduler
        scheduler.completeReservations();

        String message = String.format("Manual completion job executed successfully. Marked %d reservation(s) as COMPLETED.", count);
        log.info(message);

        return ResponseEntity.ok(message);
    }
}
