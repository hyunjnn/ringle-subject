package org.example.ringlesubject.domain.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.ringlesubject.domain.booking.enums.BookingStatus;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

@Getter
@Builder
@AllArgsConstructor
public class BookingResDto {

//    private long bookingId;
//    private String bookingStatus;
    private Long bookingId;
    private LessonDuration lessonDuration;
    private BookingStatus bookingStatus;
    private String tutorName;
    private String time;
}
