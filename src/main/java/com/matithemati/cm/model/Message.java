package com.matithemati.cm.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long id;
    private String message;
    @Column(name = "customer_id")
    private long customerId;

    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Customer customer;
}
