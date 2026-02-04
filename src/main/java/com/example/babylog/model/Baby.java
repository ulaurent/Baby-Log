package com.example.babylog.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Baby {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Explicitly add @Setter to ensure setId is generated

    private String name;

    private String birthDate;

    @ElementCollection
    private List<String> events;
}