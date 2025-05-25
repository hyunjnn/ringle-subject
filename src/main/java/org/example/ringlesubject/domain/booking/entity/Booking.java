package org.example.ringlesubject.domain.booking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.ringlesubject.domain.booking.enums.BookingStatus;
import org.example.ringlesubject.domain.lesson.entity.StudentLessonPackage;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.student.entity.Student;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_schedule_id", nullable = false)
    private TutorSchedule tutorSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_lesson_package_id")
    private StudentLessonPackage usedLessonPackage;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus bookingStatus = BookingStatus.success;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_duration", nullable = false)
    private LessonDuration lessonDuration;
}
