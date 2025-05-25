package org.example.ringlesubject.domain.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.ringlesubject.domain.common.entity.BaseTimeEntity;
import org.example.ringlesubject.domain.common.enums.Active;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_email", nullable = false, unique = true)
    private String studentEmail;

    @Column(name = "student_phone_number", nullable = false, length = 50)
    private String studentPhoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private Active studentStatus = Active.ACTIVE;

    @Column(name = "student_time_zone", nullable = false)
    private String studentTimeZone = "UTC";
}

