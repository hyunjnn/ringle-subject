package org.example.ringlesubject.domain.lesson.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

@Getter
@Builder
@AllArgsConstructor
public class BookingLessonRequest {

    private Long tutorScheduleId;
    private LessonDuration lessonDuration;
}
