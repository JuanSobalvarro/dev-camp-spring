package com.payment_system_unisalle.payment_system_unisalle_backend.dtos;

import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentStatus;
import com.payment_system_unisalle.payment_system_unisalle_backend.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPayment {

    private double amount;
    private PaymentType type;
    private LocalDate localDate;
    private String studentId;

}

