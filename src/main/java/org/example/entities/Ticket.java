package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Entity
@Table(name="ticket")
@Data
public class Ticket {
    @GeneratedValue
    @Id
    private long id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable=false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable=false)
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable=false)
    private Planet toPlanetId;
}
