package com.example.demo.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


@Entity
@Getter
@Setter
@Table (name = "Account")
public class Account {

    @Id
    @SequenceGenerator(
            name="sequence",
            sequenceName = "sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence"
    )
    private Long accountNumber;

    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    private double availableBalance;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_number")
    private Customer customer;

    @Override
    public String toString() {
        return "AccountType{" +
                "accountNumber=" + accountNumber +
                ", accountType=" + accountType +
                ", availableBalance=" + availableBalance +
                ", customer=" + customer +
                '}';
    }
}