package com.springproyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproyect.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

}
