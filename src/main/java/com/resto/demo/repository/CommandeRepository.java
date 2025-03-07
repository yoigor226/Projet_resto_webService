package com.resto.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.demo.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}

