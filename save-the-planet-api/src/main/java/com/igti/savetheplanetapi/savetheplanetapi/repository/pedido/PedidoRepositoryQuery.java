package com.igti.savetheplanetapi.savetheplanetapi.repository.pedido;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepositoryQuery {
	
	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, Long codigo);
	public Page<Pedido> resumirPedidosCozinheira(Pedido pedido, Pageable pageable, Long codigo);

}
