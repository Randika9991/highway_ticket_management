package lk.ijse.payment_service.repository;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import lk.ijse.payment_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}
