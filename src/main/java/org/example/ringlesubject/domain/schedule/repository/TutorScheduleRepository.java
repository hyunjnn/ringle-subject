package org.example.ringlesubject.domain.schedule.repository;

import org.example.ringlesubject.domain.schedule.entity.TimeSlot;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorScheduleRepository extends JpaRepository<TutorSchedule, Long> {
    boolean existsByTutorAndTimeSlot(Tutor tutor, TimeSlot timeSlot);
    boolean existsByTimeSlotAndIsAvailableTrue(TimeSlot timeSlot);
    List<TutorSchedule> findByTimeSlotAndIsAvailableTrue(TimeSlot timeSlot);
    Optional<TutorSchedule> findByTutorAndTimeSlot(Tutor tutor, TimeSlot timeSlot);
    void deleteByTutorAndTimeSlot(Tutor tutor, TimeSlot timeSlot);
}
