package org.example.ringlesubject.domain.student.repository;

import org.example.ringlesubject.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
