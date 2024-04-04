package com.matithemati.cm.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cosmetic_services")
@Data
public class CosmeticService {
    @Id
    @SequenceGenerator(
            name = "cosmetic_service_sequence",
            sequenceName = "cosmetic_service_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cosmetic_service_sequence"
    )
    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private Integer duration;
}
