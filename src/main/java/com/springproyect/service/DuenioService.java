package com.springproyect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproyect.model.Duenio;
import com.springproyect.repository.DuenioRepository;

@Service
public class DuenioService {

	@Autowired
	DuenioRepository duenioRepository;
	
	public List<Duenio> listaDuenios(){
		return duenioRepository.findAll();
	}
	
	public Duenio guardarDuenio(Duenio duenio){
		return duenioRepository.save(duenio);
	}
}
