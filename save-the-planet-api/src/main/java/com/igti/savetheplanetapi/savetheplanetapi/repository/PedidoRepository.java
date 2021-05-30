package com.igti.savetheplanetapi.savetheplanetapi.repository;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
