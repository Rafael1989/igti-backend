package com.igti.savetheplanetapi.savetheplanetapi.repository.venda;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendaRepositoryQuery {
	
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo);

}
