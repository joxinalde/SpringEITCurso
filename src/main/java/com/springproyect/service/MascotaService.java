package com.springproyect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproyect.model.Duenio;
import com.springproyect.model.Mascota;
import com.springproyect.repository.DuenioRepository;
import com.springproyect.repository.MascotaRepository;

@Service
public class MascotaService {

	
	@Autowired
	private MascotaRepository mascotaRepository;
	@Autowired
	private DuenioRepository duenioRepository;
	
	public List<Mascota> listarMascotas(){
		return mascotaRepository.findAll();
	}
	
	public void guardarMascota(Mascota mascota, Long idDuenio) {
		Duenio duenio = duenioRepository.findById(idDuenio).orElseThrow(() -> new RuntimeException());
		mascota.setDuenio(duenio);
		mascotaRepository.save(mascota);
	}
}
