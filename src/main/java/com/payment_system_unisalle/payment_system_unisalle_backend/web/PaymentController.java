package com.payment_system_unisalle.payment_system_unisalle_backend.web;

import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Payment;
import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Student;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentStatus;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentType;
import com.payment_system_unisalle.payment_system_unisalle_backend.repository.PaymentRepository;
import com.payment_system_unisalle.payment_system_unisalle_backend.repository.StudentRepository;

import com.payment_system_unisalle.payment_system_unisalle_backend.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id);
    }

    @GetMapping("/paymentsByStatus/{status}")
    public List<Payment> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping("/paymentsByType/{type}")
    public List<Payment> getPaymentsByType(@PathVariable PaymentType type) {
        return paymentRepository.findByPaymentType(type);
    }

    @PutMapping("/payments/{id}/status/{status}")
    public Payment updatePaymentStatus(@PathVariable Long id, @PathVariable PaymentStatus status) {
        Payment payment = paymentRepository.findById(id);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @PostMapping(path="/payments", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("amount") double amount,
            @RequestParam("type") PaymentType type,
            @RequestParam("date") LocalDateTime date,
            @RequestParam("studentId") String studentId) throws IOException {

        return paymentService.savePayment(file, amount, type, date, studentId);
    }

    @GetMapping(value = "/paymentFile/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return paymentService.getPaymentFile(id);
    }
}
