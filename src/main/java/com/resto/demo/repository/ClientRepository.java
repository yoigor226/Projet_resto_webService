package com.resto.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.demo.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    

    public Client findByEmail(String username);
}

