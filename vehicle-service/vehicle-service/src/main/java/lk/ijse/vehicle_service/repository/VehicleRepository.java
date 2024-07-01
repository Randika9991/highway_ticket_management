package lk.ijse.vehicle_service.repository;
/*
    this application is copyright protected
    Author : kumara
    Date : 6/28/2024
*/

import lk.ijse.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
