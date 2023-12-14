package com.springproyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproyect.model.Duenio;
import com.springproyect.service.DuenioService;

@Controller
@RequestMapping("/api/duenio")
public class DuenioViewController {

	@Autowired
	private DuenioService duenioService;

	@GetMapping("/duenios")
	public String listarDuenios(Model model) {
		List<Duenio> duenios = duenioService.listaDuenios();
		model.addAllAttributes(duenios);
		return "duenios";

	}

	@GetMapping("/formuDuenio")
	public String mostrarFormularioAgregarDuenio(Model model) {
		model.addAttribute("duenio", new Duenio());
		return "duenioForm";
	}

	@PostMapping("/guardarDuenio")
	public String guardarDuenio(Duenio duenio) {
		duenioService.guardarDuenio(duenio);

		return "redirect:/duenios";
	}
}
