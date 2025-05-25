package org.example.ringlesubject.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;

import java.time.Instant;

@Entity
@Getter
@Table(name = "time_slots", uniqueConstraints = @UniqueConstraint(columnNames = "start_time"))
public class TimeSlot extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id")
    private Long timeSlotId;

    @Column(name = "start_time", nullable = false, unique = true)
    private Instant startTime;
}
