package com.pms.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "kpis")
public class KPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String department;
    private double value;

    @Column(name = "changeVsPrevMonth", nullable = false)
    private double changeVsPrevMonth;

    private double variance;
    private double target;
}
