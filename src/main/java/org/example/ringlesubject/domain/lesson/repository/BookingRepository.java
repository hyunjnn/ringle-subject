package org.example.ringlesubject.domain.lesson.repository;

import org.example.ringlesubject.domain.lesson.entity.Booking;
import org.example.ringlesubject.domain.schedule.entity.TutorSchedule;
import org.example.ringlesubject.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStudent(Student student);
    boolean existsByTutorSchedule(TutorSchedule tutorSchedule);
}
