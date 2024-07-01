package lk.ijse.gdse.ticketservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    private String ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User userName;
    private Vehicle vehicleId;
    private String date;
    private String time;
    private String amount;
    private String entryPoint;
    private String exitPoint;

}
