package org.example.ringlesubject.domain.lesson.entity;

import jakarta.persistence.*;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.ringlesubject.domain.student.entity.Student;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_lesson_packages")
public class StudentLessonPackage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_lesson_package_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_package_id")
    private LessonPackage lessonPackage;

    @Column(name = "remaining_count", nullable = false)
    private int remainingCount;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    public void decreaseRemaining() {
        if (this.remainingCount > 0) {
            this.remainingCount--;
        } else {
            throw new IllegalStateException("남은 수업권이 없습니다.");
        }
    }
}
