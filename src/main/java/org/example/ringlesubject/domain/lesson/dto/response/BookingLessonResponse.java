package org.example.ringlesubject.domain.lesson.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.ringlesubject.domain.lesson.enums.BookingStatus;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

@Getter
@Builder
@AllArgsConstructor
public class BookingLessonResponse {

    private Long bookingId;
    private LessonDuration lessonDuration;
    private BookingStatus bookingStatus;
    private String tutorName;
    private String time;
    private String startTime;
}
