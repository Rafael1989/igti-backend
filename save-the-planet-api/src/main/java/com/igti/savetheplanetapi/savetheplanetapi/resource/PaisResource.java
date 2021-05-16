package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pais;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios/paises")
public class PaisResource {
	
	@Autowired
	private PaisRepository paisRepository;
	
	@GetMapping
	public List<Pais> listar() {
		return paisRepository.findAll();
	}
}
