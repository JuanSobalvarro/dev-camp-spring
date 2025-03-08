package com.payment_system_unisalle.payment_system_unisalle_backend.entities;

import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentStatus;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentType;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;

    private String file;

    @ManyToOne
    private Student student;
}