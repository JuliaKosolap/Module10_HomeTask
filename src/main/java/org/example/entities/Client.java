package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Data
public class Client {
        public Client() {
        }

        public Client(String name) {
                this.name = name;
        }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        @Column(name = "name")
        String name;
    }

