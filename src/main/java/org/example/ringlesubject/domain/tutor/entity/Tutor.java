package org.example.ringlesubject.domain.tutor.entity;

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
@Table(name = "tutors")
public class Tutor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutor_id")
    private Long tutorId;

    @Column(name = "tutor_name", nullable = false)
    private String tutorName;

    @Column(name = "tutor_email", nullable = false, unique = true)
    private String tutorEmail;

    @Column(name = "tutor_phone_number", nullable = false, length = 50)
    private String tutorPhoneNumber;

    @Column(name = "tutor_university", nullable = false, length = 100)
    private String tutorUniversity;

    @Column(name = "tutor_major", nullable = false, length = 100)
    private String tutorMajor;

    @Column(name = "tutor_time_zone")
    private String tutorTimeZone = "UTC";

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tutor_status", nullable = false)
    private Active tutorStatus = Active.ACTIVE;
}

