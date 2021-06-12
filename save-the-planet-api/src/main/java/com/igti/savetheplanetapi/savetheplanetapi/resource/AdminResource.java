package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import com.igti.savetheplanetapi.savetheplanetapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminResource {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/pratos/resumir", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<ResumoPrato> resumirParaAdmin(PratoFilter pratoFilter, Pageable pageable) {
		return adminService.resumirParaAdmin(pratoFilter, pageable);
	}
	
	@GetMapping("/pratos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> buscarPeloCodigoPratos(@PathVariable Long codigo) {
		return adminService.buscarPeloCodigoPratos(codigo);
	}
	
	@DeleteMapping("/pratos/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void removerPrato(@PathVariable Long codigo) {
		adminService.removerPrato(codigo);
	}
	
	@PutMapping("/pratos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Prato> atualizarPrato(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		return adminService.atualizarPrato(codigo, prato);
	}

	@GetMapping("/usuarios/filtrar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Usuario> pesquisar(Usuario usuario, Pageable pageable) {
		return adminService.filtrar(usuario, pageable);
	}

	@GetMapping(value = "/usuarios/resumir", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Usuario> resumirUsuarios(Usuario usuario, Pageable pageable) {
		return adminService.resumir(usuario, pageable);
	}

	@GetMapping("/usuarios/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Usuario> buscarPeloCodigoUsuario(@PathVariable Long codigo) {
		return adminService.buscarPeloCodigoUsuario(codigo);
	}

	@DeleteMapping("/usuarios/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void removerUsuario(@PathVariable Long codigo) {
		adminService.removerUsuario(codigo);
	}

	@PutMapping("/usuarios/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		return adminService.atualizarUsuario(codigo, usuario);
	}

	@PutMapping("/pedidos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido) {
		return adminService.atualizarPedido(codigo, pedido);
	}

	@GetMapping("/pedidos/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Pedido> buscarPeloCodigoPedido(@PathVariable Long codigo) {
		return adminService.buscarPeloCodigoPedido(codigo);
	}

	@GetMapping(value = "/pedidos", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable) {
		return adminService.resumirPedidos(pedido, pageable);
	}

	@GetMapping(value = "/vendas", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable) {
		return adminService.resumirVendas(venda, pageable);
	}

}
