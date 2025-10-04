package org.itss.backtoschool.deskops.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationCompletionScheduler {

    private final ReservationRepository reservationRepository;

    // Runs every day at 00:05 AM
    @Scheduled(cron = "0 5 0 * * ?")
    public void completeReservations() {
        LocalDate today = LocalDate.now();

        List<Reservation> toComplete =
                reservationRepository.findAllByReservationDateLessThanEqualAndStatus(today, ReservationStatus.ACTIVE);

        if (toComplete.isEmpty()) {
            log.info("No reservations to complete for {}", today);
            return;
        }

        toComplete.forEach(res -> {
            res.setStatus(ReservationStatus.COMPLETED);
            reservationRepository.save(res);
            log.info("Reservation {} marked as COMPLETED", res.getId());
        });

        log.info("Completed {} reservations for {}", toComplete.size(), today);
    }
}
