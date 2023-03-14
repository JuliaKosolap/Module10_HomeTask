package org.example.entities;

import jakarta.persistence.*;;
import lombok.*;

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


}
