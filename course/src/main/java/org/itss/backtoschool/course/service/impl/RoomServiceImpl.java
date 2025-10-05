package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.mapper.ReservationMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.service.ReservationService;
import org.itss.backtoschool.course.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	@Override
	public List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(Long roomId, LocalDateTime dateEnd, LocalDateTime dateStart) {
		List<Reservation> reservations = reservationRepository.findByRoom_IdAndStatusAndReservationDateStartLessThanAndReservationDateEndGreaterThan(roomId, ReservationStatus.ACTIVE,dateStart,dateEnd);
		List<ReservationDTO> reservationDTOS = reservations.stream().map(reservationMapper::toDTO).toList();
		List<List<TimeSlot>> bookedSlots = reservationDTOS.stream().map(this::splitHoursIntoIntervals).toList();
		List<TimeSlot> allTimeSlots = new ArrayList<>();

		for (int hour = 0; hour < 24; hour++) {
			LocalDateTime start = LocalDateTime.of(dateStart.toLocalDate(), LocalTime.of(hour,0));
			LocalDateTime end = start.plusHours(1);
			allTimeSlots.add(new TimeSlot(start, end, true));
		}

		for(TimeSlot timeSlot : allTimeSlots)
		{
			boolean found = bookedSlots
					.stream()
					.flatMap(List::stream)
					.anyMatch(
							timeSlot1 -> timeSlot1.getStart().equals(timeSlot.getStart()) && timeSlot1.getEnd().equals(timeSlot.getEnd())
					);
			if(found) timeSlot.setAvailable(false);
		}

		return allTimeSlots;
	}

	public List<TimeSlot> splitHoursIntoIntervals(ReservationDTO reservationDTO){
		List<TimeSlot> intervals = new ArrayList<>();
		LocalDateTime current = reservationDTO.getReservationDateStart();
		LocalDateTime end = reservationDTO.getReservationDateEnd();

		while(current.isBefore(end)){
			LocalDateTime next = current.plusHours(1);
			if (next.isAfter(end)) {
				next = end;
			}
			intervals.add(new TimeSlot(current,next,false));
			current = next;
		}
		return intervals;
	}
}
