package com.payment_system_unisalle.payment_system_unisalle_backend.web;

import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Payment;
import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Student;
import com.payment_system_unisalle.payment_system_unisalle_backend.repository.PaymentRepository;
import com.payment_system_unisalle.payment_system_unisalle_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // Get specific student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentRepository.findByStudentId(id);
    }

    @GetMapping("/studentsByProgram/{programId}")
    public List<Student> getStudentsByProgram(@PathVariable String programId) {
        return studentRepository.findByProgramId(programId);
    }

    @GetMapping("/students/{id}/payments")
    public List<Payment> getPaymentsByStudent(@PathVariable String id) {
        return paymentRepository.findByStudentId(id);
    }
}
