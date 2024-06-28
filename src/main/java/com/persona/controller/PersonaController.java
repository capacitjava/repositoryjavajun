package com.persona.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persona.modelo.Persona;
import com.persona.repository.PersonaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



/*
 * RECURSOS, ENDPOINTS, CONTRATOS DE INTERFAZ, VERBOS
 * 
 * GET        PUT        DELETE     POST      OPTIONS
 * list      update      delete     save      -----
 * select    update      delete     insert    -----
 * 
 */

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping({ "personas" })
public class PersonaController {

	@Autowired
	private PersonaRepository repository;
	
	//ENDPOINT, RECURSO
	//URI: http://localhost:7575/personas/listar
	@GetMapping("listar")
	public List<Persona> listarPersonas() {
		List<Persona> personas = 
		(List<Persona>) repository.findAll();
		return personas;
	}
	
	//ENDPOINT, RECURSO
	//URI: http://localhost:7575/personas/   METHOD: POST   FORMAT:JSON
	@PostMapping
	public Persona agregarPersona(@RequestBody Persona persona) {
		return repository.save(persona);
	}

	//ENDPOINT, RECURSO
	//URI: http://localhost:7575/personas/id  
	@GetMapping("/{id}")
	public Optional<Persona> getPersonaById(@PathVariable int id){
		return repository.findById(id);
	}
	
	//ENDPOINT, RECURSO
	//URI: http://localhost:7575/personas/id         FORMAT:JSON
	@PutMapping("/{id}")
	public Persona modificaPersona(@RequestBody Persona persona,
	@PathVariable int id) {
		persona.setId(id);
		return repository.save(persona);
	}
	
	//ENDPOINT, RECURSO
	//URI: http://localhost:7575/personas/id	
	@DeleteMapping("/{id}")
	public void eliminarPersona(@PathVariable int id) {
		repository.deleteById(id);
	}
	
}