package org.example.ringlesubject.domain.lesson.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lesson_packages")
public class LessonPackage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_package_id")
    private Long id;

    @Column(name = "package_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_duration", nullable = false)
    private LessonDuration lessonDuration;

    @Column(name = "total_count", nullable = false)
    private int totalCount;

    @Column(name = "valid_days", nullable = false)
    private int validDays;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
