package com.springproyect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/eliminarmascota/{id]")
	public String eliminarMascota(@PathVariable Long id) {
		mascotaService.eliminarMascota(id);
		return "redirect:/mascotas";
	}

	@GetMapping("/actualizarMascota/{id}")
	public String mostrarFormularioActualizarMascota(@PathVariable Long id, Model model) {
		Mascota mascota = mascotaService.obtenerMascotaPorId(id);
		Map<String, Object> atributos = new HashMap<>();
		atributos.put("mascota", mascota);
		atributos.put("duenios", duenioService.listaDuenios());
		model.addAttribute(atributos);
		return "actualizarMascota";
	}
	
	@PostMapping("/actualizarMascota/{id}")
	public String actualizarMascota(@PathVariable Long id, @ModelAttribute Mascota mascotaActualizada, @RequestParam Long idDueno) {
		mascotaActualizada.setDuenio(duenioService.obtenerDuenioPorId(idDueno));
		mascotaService.actualizarMascota(id, mascotaActualizada);
		return "redirect:/mascotas";
	}
}
