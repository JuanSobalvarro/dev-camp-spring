package com.payment_system_unisalle.payment_system_unisalle_backend.repository;

import com.payment_system_unisalle.payment_system_unisalle_backend.entities.Payment;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentStatus;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentType;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    // Find payment by id
    Payment findById(Long id);

    // Personalized method to search payments by studentId
    List<Payment> findByStudentId(String studentId);

    // Method to find payments by paymentStatus
    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    // Method to find payments by paymentType
    List<Payment> findByPaymentType(PaymentType paymentType);

}
