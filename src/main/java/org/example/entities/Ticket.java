package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Table(name="ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    public Ticket(Timestamp createdAt, Client client, Planet fromPlanetId, Planet toPlanetId) {
        this.createdAt = createdAt;
        this.client = client;
        this.fromPlanetId = fromPlanetId;
        this.toPlanetId = toPlanetId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable=false)
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "from_planet_id", nullable=false)
    private Planet fromPlanetId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "to_planet_id", nullable=false)
    private Planet toPlanetId;
}
