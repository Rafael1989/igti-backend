package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.event.RecursoCriadoEvent;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import com.igti.savetheplanetapi.savetheplanetapi.service.PratoService;
import com.igti.savetheplanetapi.savetheplanetapi.service.UsuarioService;
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
@RequestMapping("/admin")
public class AdminResource {

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PratoService pratoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/pratos/resumir", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<ResumoPrato> resumirPratos(PratoFilter pratoFilter, Pageable pageable) {
		return pratoRepository.resumirParaAdmin(pratoFilter, pageable);
	}
	
	@GetMapping("/pratos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> buscarPeloCodigoPratos(@PathVariable Long codigo) {
		Optional<Prato> prato = pratoRepository.findById(codigo);
		return prato.isPresent() ? ResponseEntity.ok(prato.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/pratos/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void removerPrato(@PathVariable Long codigo) {
		pratoRepository.deleteById(codigo);
	}
	
	@PutMapping("/pratos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> atualizarPrato(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = pratoService.atualizarAdmin(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/usuarios/filtrar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Usuario> pesquisar(Usuario usuario, Pageable pageable) {
		return usuarioRepository.filtrar(usuario, pageable);
	}

	@GetMapping(value = "/usuarios/resumir", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Usuario> resumirUsuarios(Usuario usuario, Pageable pageable) {
		return usuarioRepository.resumir(usuario, pageable);
	}

	@GetMapping("/usuarios/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Usuario> buscarPeloCodigoUsuario(@PathVariable Long codigo) {
		Optional<Usuario> usuario = usuarioRepository.findById(codigo);
		return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/usuarios/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void removerUsuario(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}

	@PutMapping("/usuarios/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		try {
			Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
			return ResponseEntity.ok(usuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
