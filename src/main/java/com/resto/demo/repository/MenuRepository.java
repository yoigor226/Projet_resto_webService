package com.resto.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.demo.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

