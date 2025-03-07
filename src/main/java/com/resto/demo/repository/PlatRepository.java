package com.resto.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.demo.model.Plat;

public interface PlatRepository extends JpaRepository<Plat, Long> {
}

