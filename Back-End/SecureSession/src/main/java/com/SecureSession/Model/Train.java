package com.SecureSession.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Train")
public class Train {
    @Id
    private Integer trainId;
    private String trainName;
    private Integer totalTickets;
    private Integer TicketsBooked;
}
