package org.example.ringlesubject.domain.schedule.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.booking.repository.BookingRepository;
import org.example.ringlesubject.domain.schedule.dto.request.CreateTutorScheduleReqDto;
import org.example.ringlesubject.domain.schedule.dto.response.TimeSlotResDto;
import org.example.ringlesubject.domain.schedule.repository.TimeSlotRepository;
import org.example.ringlesubject.domain.schedule.repository.TutorScheduleRepository;
import org.example.ringlesubject.domain.tutor.repository.TutorRepository;
import org.springframework.stereotype.Service;
import org.example.ringlesubject.domain.schedule.entity.TimeSlot;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.tutor.entity.Tutor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorScheduleService {

    private final TutorScheduleRepository tutorScheduleRepository;
    private final BookingRepository bookingRepository;
    private final TutorRepository tutorRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Transactional
    public void createTutorSchedule(Long tutorId, CreateTutorScheduleReqDto dto) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found"));

        for (Long slotId : dto.getTimeSlotIds()) {
            TimeSlot slot = timeSlotRepository.findById(slotId)
                    .orElseThrow(() -> new IllegalArgumentException("TimeSlot not found"));
            if (!tutorScheduleRepository.existsByTutorAndTimeSlot(tutor, slot)) {
                tutorScheduleRepository.save(TutorSchedule.builder()
                        .tutor(tutor)
                        .timeSlot(slot)
                        .isAvailable(true)
                        .build());
            }
        }
    }

    @Transactional
    public void deleteTutorSchedule(Long tutorId, List<Long> timeSlotIds) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("Tutor not found"));

        for (Long slotId : timeSlotIds) {
            TimeSlot slot = timeSlotRepository.findById(slotId)
                    .orElseThrow(() -> new IllegalArgumentException("TimeSlot not found"));

            TutorSchedule schedule = tutorScheduleRepository.findByTutorAndTimeSlot(tutor, slot)
                    .orElseThrow(() -> new IllegalArgumentException("TutorSchedule not found"));

            if (bookingRepository.existsByTutorSchedule(schedule)) {
                throw new IllegalStateException("이미 예약된 시간대는 삭제할 수 없습니다.");
            }

            tutorScheduleRepository.delete(schedule);
        }
//        for (Long slotId : timeSlotIds) {
//            TimeSlot slot = timeSlotRepository.findById(slotId)
//                    .orElseThrow(() -> new IllegalArgumentException("TimeSlot not found"));
//            tutorScheduleRepository.deleteByTutorAndTimeSlot(tutor, slot);
//        }
    }

    public List<TimeSlotResDto> getAvailableTimeSlots(Instant start, Instant end) {
        return timeSlotRepository.findByStartTimeBetween(start, end).stream()
                .map(slot -> new TimeSlotResDto(slot.getTimeSlotId(), slot.getStartTime()))
                .collect(Collectors.toList());
    }
}
