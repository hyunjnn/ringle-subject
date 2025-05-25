package org.example.ringlesubject.domain.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;

@Getter
@Builder
@AllArgsConstructor
public class BookingReqDto {

//    private long timeSlotId;
//    private long tutorScheduleId;

    private Long tutorScheduleId;
    private LessonDuration lessonDuration;
}
