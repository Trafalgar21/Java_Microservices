package com.example.demo.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

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
    private Long customerNumber;

    private String customerName;

    private String customerMobile;

    @NotNull(message = "Email is required field")
    private String customerEmail;

    private String address1;

    private String address2;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    public Customer(Long customerNumber, String customerName, String customerMobile, String customerEmail, String address1, String address2) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", customerMobile='" + customerMobile + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                '}';
    }
}