package org.example.ringlesubject.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.schedule.dto.response.TimeSlotResDto;
import org.example.ringlesubject.domain.schedule.entity.TimeSlot;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.schedule.repository.TimeSlotRepository;
import org.example.ringlesubject.domain.schedule.repository.TutorScheduleRepository;
import org.example.ringlesubject.domain.tutor.dto.response.TutorResDto;
import org.example.ringlesubject.domain.student.dto.response.AvailableTutorSearchReqDto;
import org.example.ringlesubject.domain.student.dto.resquest.AvailableSlotSearchReqDto;
import org.example.ringlesubject.domain.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentScheduleService {

    private final StudentRepository studentRepository;

    private final TimeSlotRepository timeSlotRepository;
    private final TutorScheduleRepository tutorScheduleRepository;

    public List<TimeSlotResDto> findAvailableTimeSlots(AvailableSlotSearchReqDto dto) {
        List<TimeSlot> slots = timeSlotRepository.findByStartTimeBetween(dto.getStart(), dto.getEnd());

        return slots.stream()
                .filter(slot -> {
                    boolean currentAvailable = tutorScheduleRepository.existsByTimeSlotAndIsAvailableTrue(slot);
                    if (dto.getLessonDuration().is60Min()) {
                        Instant nextTime = slot.getStartTime().plusSeconds(1800);
                        return currentAvailable && timeSlotRepository.findByStartTime(nextTime)
                                .map(nextSlot -> tutorScheduleRepository.existsByTimeSlotAndIsAvailableTrue(nextSlot))
                                .orElse(false);
                    }
                    return currentAvailable;
                })
                .map(slot -> new TimeSlotResDto(slot.getTimeSlotId(), slot.getStartTime()))
                .collect(Collectors.toList());
    }

    public List<TutorResDto> findAvailableTutors(AvailableTutorSearchReqDto dto) {
        TimeSlot slot = timeSlotRepository.findById(dto.getTimeSlotId())
                .orElseThrow(() -> new IllegalArgumentException("TimeSlot not found"));

        List<TutorSchedule> currentSchedules = tutorScheduleRepository.findByTimeSlotAndIsAvailableTrue(slot);

        if (dto.getLessonDuration().is30Min()) {
            return currentSchedules.stream()
                    .map(ts -> ts.getTutor())
                    .map(t -> new TutorResDto(t.getTutorId(), t.getTutorName(), t.getTutorUniversity(), t.getTutorMajor()))
                    .collect(Collectors.toList());
        } else {
            Instant nextTime = slot.getStartTime().plusSeconds(1800);
            Optional<TimeSlot> nextSlotOpt = timeSlotRepository.findByStartTime(nextTime);
            if (nextSlotOpt.isEmpty()) return Collections.emptyList();
            TimeSlot nextSlot = nextSlotOpt.get();

            Set<Long> nextTutorIds = tutorScheduleRepository.findByTimeSlotAndIsAvailableTrue(nextSlot).stream()
                    .map(ts -> ts.getTutor().getTutorId())
                    .collect(Collectors.toSet());

            return currentSchedules.stream()
                    .filter(ts -> nextTutorIds.contains(ts.getTutor().getTutorId()))
                    .map(ts -> ts.getTutor())
                    .map(t -> new TutorResDto(t.getTutorId(), t.getTutorName(), t.getTutorUniversity(), t.getTutorMajor()))
                    .collect(Collectors.toList());
        }
    }
}
