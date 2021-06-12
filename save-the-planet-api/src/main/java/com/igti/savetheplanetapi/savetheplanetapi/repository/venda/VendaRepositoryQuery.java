package com.igti.savetheplanetapi.savetheplanetapi.repository.venda;

import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendaRepositoryQuery {
	
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo);
	public Page<Venda> resumirVendasCozinheira(Venda venda, Pageable pageable, Long codigo);
	public Page<Venda> resumirVendasEntregador(Venda venda, Pageable pageable, Long codigo);
	public List<Venda> getVendasByPedido(Long codigo);
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable);

}
