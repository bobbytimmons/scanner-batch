package com.arbitrage.scannerbatch.database.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public record Exchange(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String name,
        boolean centralized
) {
}
