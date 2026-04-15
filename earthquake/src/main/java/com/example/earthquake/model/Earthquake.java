package com.example.earthquake.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Earthquake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double magnitude;
    private String magType;
    private String place;
    private String title;
    private Long time;
}