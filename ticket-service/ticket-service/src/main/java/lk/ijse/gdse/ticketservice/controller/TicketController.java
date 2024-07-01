package lk.ijse.gdse.ticketservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @GetMapping("/getAllTickets")
    public String getAllTickets(){
        return "All Tickets";
    }
}
