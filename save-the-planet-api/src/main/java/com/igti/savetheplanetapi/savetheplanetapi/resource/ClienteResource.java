package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import com.igti.savetheplanetapi.savetheplanetapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente/pratos")
public class ClienteResource {

	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/resumir", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_CLIENTE') or hasAuthority('ROLE_ADMIN')")
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable) {
		return pratoRepository.resumirParaCliente(pratoFilter, pageable);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CLIENTE') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> comprar(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = clienteService.comprar(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
