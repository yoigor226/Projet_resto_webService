package com.resto.demo.model;



// import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToMany
    private List<Plat> plats;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @ManyToOne
    private Client client;

    private Double prixTotal;

    

    // Getters and Setters
}




