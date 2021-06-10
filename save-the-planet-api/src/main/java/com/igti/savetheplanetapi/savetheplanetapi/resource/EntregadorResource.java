package com.igti.savetheplanetapi.savetheplanetapi.resource;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
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
	private EntregadorService entregadorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/pedidos/{codigo}", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, @PathVariable Long codigo) {
		return entregadorService.resumirPedidos(pedido, pageable, codigo);
	}
	
	@PutMapping("/entregar/{codigo}/{codigoEntregador}")
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Pedido> entregar(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido, @PathVariable Long codigoEntregador) {
		try {
			Pedido pedidoSalvo = entregadorService.entregar(codigo, pedido, codigoEntregador);
			return ResponseEntity.ok(pedidoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/pagar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Pedido> pagar(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido) {
		try {
			Pedido pedidoSalvo = entregadorService.pagar(codigo, pedido);
			return ResponseEntity.ok(pedidoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/vendas/{codigo}", params = {"resumo"})
	@PreAuthorize("hasAuthority('ROLE_ENTREGADOR') or hasAuthority('ROLE_ADMIN')")
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, @PathVariable Long codigo) {
		return entregadorService.resumirVendas(venda, pageable, codigo);
	}
	
}
