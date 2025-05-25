package org.example.ringlesubject.domain.student.dto.response;

import lombok.Getter;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

@Getter
public class AvailableTutorSearchReqDto {
    private Long timeSlotId;
    private LessonDuration lessonDuration;
}
