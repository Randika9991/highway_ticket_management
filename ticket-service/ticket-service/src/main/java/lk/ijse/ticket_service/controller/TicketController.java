package lk.ijse.ticket_service.controller;/*
    this application is copyright protected
    Author : kumara
    Date : 6/26/2024
*/

import lk.ijse.ticket_service.entity.Ticket;
import lk.ijse.ticket_service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    TicketController() {
        System.out.println("vehicle invoked");
    }

//    @GetMapping
//    public String get(){
//        return "Ticket get method invoked";
//    }

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public boolean registerTicket() {
        System.out.println("ticker invoke");
//        try {
//            Ticket savedTicket = ticketService.saveTicket(ticket);
//            return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return true;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllVehicles() {
        return ResponseEntity.ok(ticketService.getAllTicket());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateVehicle(@PathVariable Long id, @RequestBody Ticket vehicle) {
        return ResponseEntity.ok(ticketService.updateTicket(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
