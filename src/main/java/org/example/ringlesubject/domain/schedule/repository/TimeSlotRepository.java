package org.example.ringlesubject.domain.schedule.repository;

import org.example.ringlesubject.domain.schedule.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByStartTimeBetween(Instant start, Instant end);
    Optional<TimeSlot> findByStartTime(Instant startTime);
}
