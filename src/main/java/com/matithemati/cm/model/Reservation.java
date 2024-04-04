package com.matithemati.cm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "cosmetic_service_id")
    private Long cosmeticServiceId;
    private LocalDateTime reservationDateTime;
    private String status;
    private int rating = 0;

    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "cosmetic_service_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private CosmeticService cosmeticService;

    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Customer customer;
}
