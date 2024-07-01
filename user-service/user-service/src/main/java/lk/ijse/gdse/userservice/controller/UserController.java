package lk.ijse.gdse.userservice.controller;/*
    this application is copyright protected
    Author : kumara
    Date : 6/30/2024
*/

import lk.ijse.gdse.userservice.entity.User;
import lk.ijse.gdse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    UserController() {
        System.out.println("user invoked");
    }

//    @GetMapping
//    public String get(){
//        return "user get method invoked";
//    }

    @PostMapping
    public ResponseEntity<User> registerVehicle(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getVehicle(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUser(name));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllVehicles() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateVehicle(@PathVariable String name, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(name, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String name) {
        userService.deleteUser(name);
        return ResponseEntity.noContent().build();
    }
}
