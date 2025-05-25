package org.example.ringlesubject.domain.lesson.repository;

import org.example.ringlesubject.domain.lesson.entity.StudentLessonPackage;
import org.example.ringlesubject.domain.lesson.enums.LessonDuration;
import org.example.ringlesubject.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentLessonPackageRepository extends JpaRepository<StudentLessonPackage, Long> {
    Optional<StudentLessonPackage> findFirstByStudentAndLessonPackage_LessonDuration(Student student, LessonDuration lessonDuration);
}
