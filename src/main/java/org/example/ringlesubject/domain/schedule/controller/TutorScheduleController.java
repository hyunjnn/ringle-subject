package org.example.ringlesubject.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.schedule.dto.request.CreateTutorScheduleReqDto;
import org.example.ringlesubject.domain.schedule.dto.response.TimeSlotResDto;
import org.example.ringlesubject.domain.schedule.service.TutorScheduleService;
import org.example.ringlesubject.global.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tutors")
public class TutorScheduleController {

    private final TutorScheduleService tutorScheduleService;

    @PostMapping("/{tutorId}/schedules")
    public ApiResponse<?> create(@PathVariable Long tutorId, @RequestBody CreateTutorScheduleReqDto requestDto) {
        tutorScheduleService.createTutorSchedule(tutorId, requestDto);
        return ApiResponse.ok(null);
    }

    @DeleteMapping("/{tutorId}/schedules")
    public ApiResponse<?> delete(@PathVariable Long tutorId, @RequestBody List<Long> slotIds) {
        tutorScheduleService.deleteTutorSchedule(tutorId, slotIds);
        return ApiResponse.ok(null);
    }

    @GetMapping("/available-slots")
    public ApiResponse<List<TimeSlotResDto>> getAvailableSlots(
            @RequestParam Instant start,
            @RequestParam Instant end
    ) {
        return ApiResponse.ok(tutorScheduleService.getAvailableTimeSlots(start, end));
    }
}
