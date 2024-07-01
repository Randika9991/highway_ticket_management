package lk.ijse.gdse.userservice.service;/*
    this application is copyright protected
    Author : kumara
    Date : 6/30/2024
*/

import lk.ijse.gdse.userservice.entity.User;
import lk.ijse.gdse.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(String name) {
        return userRepo.findById(name).orElse(null);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User updateUser(String id, User user) {
        User existingVehicle = userRepo.findById(id).orElse(null);
        if (existingVehicle != null) {
            existingVehicle.setUserName(user.getUserName());
            existingVehicle.setEmail(user.getEmail());
            existingVehicle.setPassword(user.getPassword());
            return userRepo.save(existingVehicle);
        }
        return null;
    }

    public boolean deleteUser(String name) {
        userRepo.deleteById(name);
        return false;
    }
}
