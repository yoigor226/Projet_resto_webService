package com.resto.demo.model;



// import javax.persistence.*;


import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double prix;
    private String description;
    private String categorie;

    @ElementCollection
    private List<String> allergènes;

    private Boolean statut; // disponible/non disponible

    // Getters and Setters
}

