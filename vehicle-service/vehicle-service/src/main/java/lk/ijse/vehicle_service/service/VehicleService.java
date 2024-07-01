package lk.ijse.vehicle_service.service;

/*
    this application is copyright protected
    Author : kumara
    Date : 6/28/2024
*/

import lk.ijse.vehicle_service.entity.User;
import lk.ijse.vehicle_service.entity.Vehicle;
import lk.ijse.vehicle_service.repository.UserRepo;
import lk.ijse.vehicle_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepo userRepo;

    public Vehicle registerVehicle(Vehicle vehicle) {
        try {
            User owner = userRepo.findById(vehicle.getOwner().getUserName())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            vehicle.setOwner(owner);
            return vehicleRepository.save(vehicle);

        } catch (Exception e) {
            throw new RuntimeException("Error registering vehicle: " + e.getMessage(), e);
        }
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElse(null);
        if (existingVehicle != null) {
            existingVehicle.setLicensePlate(vehicle.getLicensePlate());
            existingVehicle.setMake(vehicle.getMake());
            existingVehicle.setModel(vehicle.getModel());
            User owner = userRepo.findById(vehicle.getOwner().getUserName())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            existingVehicle.setOwner(owner);

            return vehicleRepository.save(existingVehicle);
        }
        return null;
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}

