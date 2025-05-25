package org.example.ringlesubject.domain.booking.controller;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.booking.dto.request.BookingReqDto;
import org.example.ringlesubject.domain.booking.dto.response.BookingResDto;
import org.example.ringlesubject.domain.booking.service.BookingService;
import org.springframework.web.bind.annotation.*;
import org.example.ringlesubject.global.response.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{userId}/bookings")
    public ApiResponse<BookingResDto> bookLesson(@PathVariable Long userId, @RequestBody BookingReqDto dto) {
        return ApiResponse.ok(bookingService.createBooking(userId, dto));
    }

    @GetMapping("/{userId}/bookings")
    public ApiResponse<List<BookingResDto>> getBookings(@PathVariable Long userId) {
        return ApiResponse.ok(bookingService.getUserBookings(userId));
    }
}