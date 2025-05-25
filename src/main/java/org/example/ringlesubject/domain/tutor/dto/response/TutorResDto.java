package org.example.ringlesubject.domain.tutor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TutorResDto {
    private Long tutorId;
    private String name;
    private String university;
    private String major;
}
