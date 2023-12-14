package com.springproyect.service;

import java.util.List;
import java.util.Optional;

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
		Duenio duenio = duenioRepository.findById(idDuenio).orElseThrow(() -> new RuntimeException("No se encontro al duenio"));
		mascota.setDuenio(duenio);
		mascotaRepository.save(mascota);
	}
	
	public Mascota obtenerMascotaPorId(Long id) {
		return mascotaRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la mascota"));
	}
	
	public void eliminarMascota(Long id) {
		Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
		
		if (!mascotaOptional.isPresent()) {
			throw new RuntimeException("No se encontro la mascota");
		} else {
			Mascota mascotaExistente = mascotaOptional.get();
			mascotaRepository.delete(mascotaExistente);
		}
	}
	
	public void actualizarMascota(Long id, Mascota mascotaActualizada) {
		Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
		
		if (!mascotaOptional.isPresent()) {
			throw new RuntimeException("No se encontro la mascota");
		} else {
			Mascota mascotaExistente = mascotaOptional.get();
			mascotaExistente.setNombre(mascotaActualizada.getNombre());
			mascotaExistente.setEdad(mascotaActualizada.getEdad());
			mascotaExistente.setDuenio(mascotaActualizada.getDuenio());
			mascotaRepository.save(mascotaExistente);
		}
	}
}
