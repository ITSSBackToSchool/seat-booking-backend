package org.itss.backtoschool.deskops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for manually triggering scheduled tasks.
 * Useful for testing and administrative operations.
 */
@RequestMapping("/api/admin/scheduler")
public interface SchedulerController {

    /**
     * Manually triggers the reservation completion job.
     * This runs the same logic as the daily scheduled task.
     *
     * @return Response with the number of reservations completed
     */
    @PostMapping("/complete-reservations")
    ResponseEntity<String> triggerReservationCompletion();
}
