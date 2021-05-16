package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Estado;
import com.igti.savetheplanetapi.savetheplanetapi.repository.EstadoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> pesquisar(@RequestParam Long pais) {
		return estadoRepository.findByPaisCodigo(pais);
	}

}
