package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.SchedulerController;
import org.itss.backtoschool.deskops.scheduler.ReservationCompletionScheduler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchedulerControllerImpl implements SchedulerController {

    private final ReservationCompletionScheduler scheduler;

    @Override
    public String triggerCompletion() {
        scheduler.completeReservations();
        return "Manual completion job executed.";
    }
}
