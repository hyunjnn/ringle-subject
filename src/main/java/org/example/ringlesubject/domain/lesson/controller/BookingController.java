package org.example.ringlesubject.domain.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.lesson.dto.request.BookingLessonRequest;
import org.example.ringlesubject.domain.lesson.dto.response.BookingLessonResponse;
import org.example.ringlesubject.domain.lesson.service.BookingService;
import org.springframework.web.bind.annotation.*;
import org.example.ringlesubject.global.response.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{userId}/bookings")
    public ApiResponse<BookingLessonResponse> bookLesson(@PathVariable Long userId, @RequestBody BookingLessonRequest dto) {
        return ApiResponse.ok(bookingService.createBooking(userId, dto));
    }

    @GetMapping("/{userId}/bookings")
    public ApiResponse<List<BookingLessonResponse>> getBookings(@PathVariable Long userId) {
        return ApiResponse.ok(bookingService.getUserBookings(userId));
    }
}