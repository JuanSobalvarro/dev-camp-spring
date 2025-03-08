package com.payment_system_unisalle.payment_system_unisalle_backend.service;

import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Payment;

import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Student;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentStatus;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentType;

import com.payment_system_unisalle.payment_system_unisalle_backend.repository.PaymentRepository;
import com.payment_system_unisalle.payment_system_unisalle_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentService {

    // Dependency injection to interact with the database
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    /*
     * @param file PDF file to be uploaded
     * @param amount Amount to be paid
     * @param type Type of payment
     * @param date Date of the payment
     * @param studentId Student ID
     * @return Payment object
     * @throws IOException
     */

    // Constructor where the route to the file is saved
    public Payment savePayment(MultipartFile file, double amount, PaymentType type, LocalDateTime date, String studentId) throws IOException {

        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        // Generate a UUID for the file
        String filename = java.util.UUID.randomUUID().toString() + ".pdf";

        // Construct full route
        Path filePath = Paths.get(folderPath.toString(), filename);

        // Save the file
        Files.copy(file.getInputStream(), filePath);

        // Search in the database for the student with the given ID
        Student student = studentRepository.findByStudentId(studentId);

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        // Create a new payment object
        Payment payment = Payment.builder()
                .amount(amount)
                .type(type)
                .date(date)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.PENDING)
                .student(student)
                .build(); // Final construction of the object

        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId);

        // Obtain uri and convert to url
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }

    public Payment updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId);

        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }

        payment.setStatus(status);

        return paymentRepository.save(payment);
    }
}
