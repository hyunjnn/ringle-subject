package org.example.ringlesubject.domain.booking.service;

import lombok.RequiredArgsConstructor;
import org.example.ringlesubject.domain.booking.dto.request.BookingReqDto;
import org.example.ringlesubject.domain.booking.dto.response.BookingResDto;
import org.example.ringlesubject.domain.booking.entity.Booking;
import org.example.ringlesubject.domain.booking.enums.BookingStatus;
import org.example.ringlesubject.domain.booking.repository.BookingRepository;
import org.example.ringlesubject.domain.lesson.entity.StudentLessonPackage;
import org.example.ringlesubject.domain.lesson.repository.StudentLessonPackageRepository;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.schedule.repository.TutorScheduleRepository;
import org.example.ringlesubject.domain.student.entity.Student;
import org.example.ringlesubject.domain.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final StudentRepository studentRepository;
    private final TutorScheduleRepository tutorScheduleRepository;
    private final StudentLessonPackageRepository studentLessonPackageRepository;

    @Transactional
    public BookingResDto createBooking(Long userId, BookingReqDto dto) {
        Student student = studentRepository.findById(userId).orElseThrow();
        TutorSchedule schedule = tutorScheduleRepository.findById(dto.getTutorScheduleId()).orElseThrow();

        if (!schedule.isAvailable()) {
            throw new IllegalStateException("해당 시간은 이미 예약되었습니다.");
        }

        StudentLessonPackage studentLesson = studentLessonPackageRepository.findFirstByStudentAndLessonPackage_LessonDuration(student, dto.getLessonDuration())
                .orElseThrow(() -> new IllegalStateException("사용 가능한 수업권이 없습니다."));

        if (studentLesson.getRemainingCount() <= 0) {
            throw new IllegalStateException("수업권 잔여 횟수가 없습니다.");
        }

        Booking booking = Booking.builder()
                .student(student)
                .tutorSchedule(schedule)
                .usedLessonPackage(studentLesson)
                .lessonDuration(dto.getLessonDuration())
                .bookingStatus(BookingStatus.success)
                .build();

        bookingRepository.save(booking);
        schedule.setAvailable(false);
        studentLesson.decreaseRemaining();

        return new BookingResDto(
                booking.getBookingId(),
                booking.getLessonDuration(),
                booking.getBookingStatus(),
                schedule.getTutor().getTutorName(),
                schedule.getTimeSlot().getStartTime().toString()
        );
    }

    public List<BookingResDto> getUserBookings(Long userId) {
        Student student = studentRepository.findById(userId).orElseThrow();
        return bookingRepository.findByStudent(student).stream()
                .map(b -> new BookingResDto(
                        b.getBookingId(),
                        b.getLessonDuration(),
                        b.getBookingStatus(),
                        b.getTutorSchedule().getTutor().getTutorName(),
                        b.getTutorSchedule().getTimeSlot().getStartTime().toString()
                ))
                .collect(Collectors.toList());
    }
}
