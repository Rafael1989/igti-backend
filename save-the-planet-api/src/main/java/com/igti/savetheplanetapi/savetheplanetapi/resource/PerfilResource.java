package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Perfil;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilResource {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping
	public List<Perfil> listar() {
		return perfilRepository.findAll();
	}
	
}
