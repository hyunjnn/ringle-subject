package org.example.ringlesubject.domain.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class TimeSlotResDto {
    private Long timeSlotId;
    private Instant startTime;
}
