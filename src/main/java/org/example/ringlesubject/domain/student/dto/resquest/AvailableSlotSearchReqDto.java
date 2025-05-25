package org.example.ringlesubject.domain.student.dto.resquest;

import lombok.Getter;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

import java.time.Instant;

@Getter
public class AvailableSlotSearchReqDto {
    private Instant start;
    private Instant end;
    private LessonDuration lessonDuration;
}