package lk.ijse.ticket_service.service;/*
    this application is copyright protected
    Author : kumara
    Date : 7/1/2024
*/

import lk.ijse.ticket_service.entity.Ticket;
import lk.ijse.ticket_service.entity.User;
import lk.ijse.ticket_service.entity.Vehicle;
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

    public Ticket saveTicket(Ticket ticket) {

        try {
            User owner = userRepo.findById(ticket.getUserName().getUserName())
                    .orElseThrow(() -> {
                        System.out.println("User not found");
                        return new IllegalArgumentException("User not found");
                    });
            ticket.setUserName(owner);

            Vehicle vehicleId = vehicleRepository.findById(ticket.getId().getId()).orElseThrow(() -> {
                System.out.println("Vehicle not found");
                return new IllegalArgumentException("Vehicle not found");
            });
            ticket.setId(vehicleId);

            return ticketRepo.save(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Error registering vehicle: " + e.getMessage(), e);
        }
    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public List<Ticket> getAllTicket() {
        return ticketRepo.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        try {
            Ticket existingTicket = ticketRepo.findById(id).orElseThrow(() -> {
                System.out.println("Ticket not found");
                return new IllegalArgumentException("Ticket not found");
            });

            User owner = userRepo.findById(ticket.getUserName().getUserName())
                    .orElseThrow(() -> {
                        System.out.println("User not found");
                        return new IllegalArgumentException("User not found");
                    });
            existingTicket.setUserName(owner);

            Vehicle vehicle = vehicleRepository.findById(ticket.getId().getId()).orElseThrow(() -> {
                System.out.println("Vehicle not found");
                return new IllegalArgumentException("Vehicle not found");
            });
            existingTicket.setId(vehicle);

            existingTicket.setDate(ticket.getDate());
            existingTicket.setTime(ticket.getTime());
            existingTicket.setAmount(ticket.getAmount());
            existingTicket.setEntryPoint(ticket.getEntryPoint());
            existingTicket.setExitPoint(ticket.getExitPoint());

            return ticketRepo.save(existingTicket);

        } catch (Exception e) {
            throw new RuntimeException("Error updating ticket: " + e.getMessage(), e);
        }
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
