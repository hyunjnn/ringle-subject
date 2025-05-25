package org.example.ringlesubject.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.ringlesubject.domain.tutor.entity.Tutor;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutor_schedules")
public class TutorSchedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutor_schedule_id")
    private Long tutorScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @Column(name = "tutor_schedule_is_available", nullable = false)
    private boolean isAvailable = true;

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
