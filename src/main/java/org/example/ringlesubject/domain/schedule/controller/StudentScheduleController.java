package org.example.ringlesubject.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.schedule.dto.response.TimeSlotResDto;
import org.example.ringlesubject.domain.schedule.service.StudentScheduleService;
import org.example.ringlesubject.domain.student.dto.response.AvailableTutorSearchReqDto;
import org.example.ringlesubject.domain.student.dto.resquest.AvailableSlotSearchReqDto;
import org.example.ringlesubject.domain.tutor.dto.response.TutorResDto;
import org.example.ringlesubject.global.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentScheduleController {

    private final StudentScheduleService studentScheduleService;

    @PostMapping("/available-slots")
    public ApiResponse<List<TimeSlotResDto>> getAvailableSlots(@RequestBody AvailableSlotSearchReqDto dto) {
        return ApiResponse.ok(studentScheduleService.findAvailableTimeSlots(dto));
    }

    @PostMapping("/available-tutors")
    public ApiResponse<List<TutorResDto>> getAvailableTutors(@RequestBody AvailableTutorSearchReqDto dto) {
        return ApiResponse.ok(studentScheduleService.findAvailableTutors(dto));
    }
}
