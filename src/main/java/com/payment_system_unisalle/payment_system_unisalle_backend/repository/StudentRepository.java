package com.payment_system_unisalle.payment_system_unisalle_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByStudentId(String studentId);

    List<Student> findByProgramId(String programId);

}
