package lk.ijse.payment_service.service;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ServiceUnavailableException;
import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.entity.Ticket;
import lk.ijse.payment_service.repository.PaymentRepo;
import lk.ijse.payment_service.service.util.TicketStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper mapper;

    public Payment PaymentPlacePart2(Payment payment) {
        if (paymentRepo.existsById(payment.getId())) {
            System.out.println("Payment ID already exists!");
        }

        Payment pay = mapper.map(payment, Payment.class);
        Ticket ticket;
        try {
            ticket = restTemplate.getForObject("http://TICKET-SERVICE/api/v1/ticket/" + payment.getTicketId().getTicketId(), Ticket.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ticket not found: " + payment.getTicketId());
        }

        if (ticket != null) {
            ticket.setStatus(TicketStatus.PAID);
            updateTicketStatus(ticket);
        }

        pay.setTicketId(mapper.map(ticket, Ticket.class));
        return mapper.map(paymentRepo.save(payment), Payment.class);
    }

//    public Payment placePayment(Payment payment) {
//        if (paymentRepo.existsById(payment.getId())) {
//            System.out.println("Payment ID already exists!");
//        }
//
//        Ticket ticket;
//        try {
//            ticket = restTemplate.getForObject("http://TICKET-SERVICE/api/v1/ticket/" + payment.getTicketId(), Ticket.class);
//        } catch (HttpClientErrorException.NotFound e) {
//            throw new NotFoundException("Ticket not found: " + payment.getTicketId());
//        }
//
//        if (ticket != null) {
//            ticket.setStatus(TicketStatus.PAID);
//            updateTicketStatus(ticket);
//        }
//
//        Payment pay = mapper.map(payment, Payment.class);
////        pay.setTicketId(ticket.getTicketId());
//        return mapper.map(paymentRepo.save(pay), Payment.class);
//    }


    public boolean updateTicketStatus(Ticket ticketDTO){
        try {
            Ticket newDTO = restTemplate.postForObject("http://TICKET-SERVICE/api/v1/ticket", ticketDTO, Ticket.class);
            return newDTO != null;
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ticket not found " + ticketDTO.getTicketId());
        }  catch (RestClientException e) {
            throw new ServiceUnavailableException("Error communicating with Ticket service "+ e);
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll().stream().map(payment -> mapper.map(payment,Payment.class)).toList();
    }
}
