package com.resto.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resto.demo.model.Plat;
import com.resto.demo.repository.PlatRepository;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat getPlatById(Long id) {
        return platRepository.findById(id).orElse(null);
    }

    public Plat savePlat(Plat plat) {
        return platRepository.save(plat);
    }

    public void deletePlat(Long id) {
        platRepository.deleteById(id);
    }
}

