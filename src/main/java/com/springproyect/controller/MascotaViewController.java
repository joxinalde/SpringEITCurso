package com.springproyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproyect.model.Mascota;
import com.springproyect.service.DuenioService;
import com.springproyect.service.MascotaService;

@Controller
@RequestMapping("/api/mascota")
public class MascotaViewController {

	
	@Autowired
	private MascotaService mascotaService;

	@Autowired
	private DuenioService duenioService;
	
	@GetMapping("/mascotas")
	public String listarMascota(Model model) {
		List<Mascota> mascotas = mascotaService.listarMascotas();
		model.addAllAttributes(mascotas);
		return "mascotas";
	}
	

	@GetMapping("/formuMascota")
	public String mostrarFormularioAgregarMascota(Model model) {
		model.addAttribute("duenios", duenioService.listaDuenios());
		return "formuMascota";
	}

	@PostMapping("/guardarMascota")
	public String guardarMascota(Mascota mascota, @RequestParam Long idDueno) {
		mascotaService.guardarMascota(mascota, idDueno);
		return "redirect:/mascotas";
	}
}
