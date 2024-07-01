package lk.ijse.ticket_service.repository;/*
    this application is copyright protected
    Author : kumara
    Date : 7/1/2024
*/


import lk.ijse.ticket_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
