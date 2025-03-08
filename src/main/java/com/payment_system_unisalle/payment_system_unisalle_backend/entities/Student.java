package com.payment_system_unisalle.payment_system_unisalle_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;

    private String name;
    private String lastName;

    @Column(unique = true)
    private String studentId;

    private String programId;

    private String photo;
}
