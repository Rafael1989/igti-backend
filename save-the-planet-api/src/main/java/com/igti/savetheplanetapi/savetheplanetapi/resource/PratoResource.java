package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.event.RecursoCriadoEvent;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import com.igti.savetheplanetapi.savetheplanetapi.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pratos")
public class PratoResource {

	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private PratoService pratoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/filtrar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public Page<Prato> pesquisar(PratoFilter pratoFilter, Pageable pageable, @PathVariable Long codigo) {
		return pratoRepository.filtrar(pratoFilter, pageable, codigo);
	}
	
	@GetMapping(value = "/resumir/{codigo}", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable, @PathVariable Long codigo) {
		return pratoRepository.resumir(pratoFilter, pageable, codigo);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Prato> prato = pratoRepository.findById(codigo);
		return prato.isPresent() ? ResponseEntity.ok(prato.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> criar(@Valid @RequestBody Prato prato, HttpServletResponse response) {
		Prato pratoSalvo = pratoService.salvar(prato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pratoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pratoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public void remover(@PathVariable Long codigo) {
		pratoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_COZINHEIRA') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> atualizar(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = pratoService.atualizar(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
