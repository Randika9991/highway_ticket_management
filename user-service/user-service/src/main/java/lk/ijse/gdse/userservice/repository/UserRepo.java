package lk.ijse.gdse.userservice.repository;/*
    this application is copyright protected
    Author : kumara
    Date : 6/30/2024
*/

import lk.ijse.gdse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
