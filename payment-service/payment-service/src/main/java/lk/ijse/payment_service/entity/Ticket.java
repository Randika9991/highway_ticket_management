package lk.ijse.payment_service.entity;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User userName;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle id;

    private String date;
    private String time;
    private String amount;
    private String entryPoint;
    private String exitPoint;
}

