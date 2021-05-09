package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import com.igti.savetheplanetapi.savetheplanetapi.service.EntregadorService;
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
@RequestMapping("/entregador/pratos")
public class EntregadorResource {

	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private EntregadorService entregadorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/resumir/{codigo}", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable, @PathVariable Long codigo) {
		return pratoRepository.resumirParaEntregador(pratoFilter, pageable, codigo);
	}
	
	@PutMapping("/entregar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> entregar(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = entregadorService.entregar(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/pagar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> pagar(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = entregadorService.pagar(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
