package lk.ijse.payment_service.controller;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.entity.Ticket;
import lk.ijse.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final RestTemplate restTemplate;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(RestTemplate restTemplate, PaymentService paymentService) {
        this.restTemplate = restTemplate;
        this.paymentService = paymentService;
        System.out.println("PaymentController invoked");
    }

    @PostMapping
    public ResponseEntity<Payment> registerTicket(@RequestBody Payment payment) {
        String amount = payment.getAmount();
        Ticket ticketId = payment.getTicketId();
        String paymentServiceUrl = "http://localhost:8083/api/v1/ticket";
        Payment request = new Payment(ticketId, amount);

        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<Payment> requestEntity = new HttpEntity<>(request, headers);

            // Use RestTemplate to send POST request
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(paymentServiceUrl, requestEntity, Object.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // Assuming the response is a Payment object, you would need to map it correctly
                Payment savedPayment = (Payment) responseEntity.getBody(); // Adjust this based on actual response structure

                // Save the payment in the local database
                savedPayment = paymentService.savePayment(savedPayment);
                return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
            } else {
                // Handle other HTTP status codes as needed
                return new ResponseEntity<>(null, responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payment> getVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllVehicles() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updateVehicle(@PathVariable Long id, @RequestBody Payment vehicle) {
        return ResponseEntity.ok(paymentService.updatePayment(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
