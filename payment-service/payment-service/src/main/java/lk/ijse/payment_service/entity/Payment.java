package lk.ijse.payment_service.entity;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticketId;

    private String amount;
    private String status; // Pending, Completed, Failed, etc.

    public Payment(Ticket ticketId, String amount) {
        this.ticketId = ticketId;
        this.amount = amount;
    }
}
