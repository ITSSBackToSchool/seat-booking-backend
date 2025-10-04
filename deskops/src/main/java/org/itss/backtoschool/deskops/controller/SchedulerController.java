package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.scheduler.ReservationCompletionScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/scheduler")
public interface SchedulerController {

    @PostMapping("/complete-reservations")
    String triggerCompletion();
}
