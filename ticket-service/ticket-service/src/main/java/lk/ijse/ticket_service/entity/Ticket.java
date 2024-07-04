package lk.ijse.ticket_service.entity;/*
    this application is copyright protected
    Author : kumara
    Date : 7/1/2024
*/

import jakarta.persistence.*;
import lk.ijse.ticket_service.service.util.TicketStatus;
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

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
