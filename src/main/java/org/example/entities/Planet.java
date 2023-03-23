package org.example.entities;

import jakarta.persistence.*;;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="planet")
@Data
public class Planet {

    public Planet() {}
    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id
    private String id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "fromPlanetId")
    private Set<Ticket> ticketFrom;


    @OneToMany(mappedBy = "toPlanetId")
    private Set<Ticket> ticketTo;

}
