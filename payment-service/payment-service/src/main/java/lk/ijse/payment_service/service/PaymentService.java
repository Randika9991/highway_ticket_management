package lk.ijse.payment_service.service;/*
    this application is copyright protected
    Author : kumara
    Date : 7/2/2024
*/

import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment savePayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = paymentRepo.findById(id).orElse(null);
        if (existingPayment != null) {
            existingPayment.setTicketId(updatedPayment.getTicketId());
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setStatus(updatedPayment.getStatus());
            return paymentRepo.save(existingPayment);
        }
        return null;
    }

    public boolean deletePayment(Long id) {
        paymentRepo.deleteById(id);
        return true;
    }
}
