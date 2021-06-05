package com.igti.savetheplanetapi.savetheplanetapi.repository;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.repository.pedido.PedidoRepositoryQuery;
import com.igti.savetheplanetapi.savetheplanetapi.repository.prato.PratoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQuery {
	
}
