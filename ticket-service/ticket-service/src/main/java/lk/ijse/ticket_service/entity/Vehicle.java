package lk.ijse.ticket_service.entity;/*
    this application is copyright protected
    Author : kumara
    Date : 7/1/2024
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensePlate;
    private String make;
    private String model;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User owner;
//    private String name;

}