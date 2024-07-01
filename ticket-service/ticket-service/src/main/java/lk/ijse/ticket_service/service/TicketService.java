package lk.ijse.ticket_service.service;/*
    this application is copyright protected
    Author : kumara
    Date : 7/1/2024
*/

import lk.ijse.ticket_service.entity.Ticket;
import lk.ijse.ticket_service.repository.TicketRepo;
import lk.ijse.ticket_service.repository.UserRepo;
import lk.ijse.ticket_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepo userRepo;

    public Ticket saveTicket(Ticket user) {
        return ticketRepo.save(user);
    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public List<Ticket> getAllTicket() {
        return ticketRepo.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = ticketRepo.findById(id).orElse(null);
        if (existingTicket != null) {
            existingTicket.setTicketId(ticket.getTicketId());
            existingTicket.setId(ticket.getId());
            existingTicket.setDate(ticket.getDate());
            existingTicket.setTime(ticket.getTime());
            existingTicket.setAmount(ticket.getAmount());
            existingTicket.setEntryPoint(ticket.getEntryPoint());
            existingTicket.setExitPoint(ticket.getExitPoint());
            return ticketRepo.save(existingTicket);
        }
        return null;
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
