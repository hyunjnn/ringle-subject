package org.example.ringlesubject.domain.schedule.dto.request;

import lombok.Getter;
import java.util.List;

@Getter
public class CreateTutorScheduleReqDto {
    private List<Long> timeSlotIds;
}
