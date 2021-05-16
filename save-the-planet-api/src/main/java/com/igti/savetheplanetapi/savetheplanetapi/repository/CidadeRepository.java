package com.igti.savetheplanetapi.savetheplanetapi.repository;

import com.igti.savetheplanetapi.savetheplanetapi.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	List<Cidade> findByEstadoCodigo(Long estadoCodigo);

}
