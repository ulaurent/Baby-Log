package com.example.babylog.model;

import lombok.Data;

@Data
public class Baby {
    private String id; // Explicitly add @Setter to ensure setId is generated
    private String name;
    private String birthDate;
}