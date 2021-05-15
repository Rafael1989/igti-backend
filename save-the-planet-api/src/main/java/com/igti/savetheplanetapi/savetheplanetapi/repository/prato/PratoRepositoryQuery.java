package com.igti.savetheplanetapi.savetheplanetapi.repository.prato;

import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PratoRepositoryQuery {
	
	public Page<Prato> filtrar(PratoFilter pratoFilter, Pageable pageable, Long codigo);
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable, Long codigo);
	public Page<ResumoPrato> resumirParaAdmin(PratoFilter pratoFilter, Pageable pageable);
	public Page<ResumoPrato> resumirParaCliente(PratoFilter pratoFilter, Pageable pageable);
	public Page<ResumoPrato> resumirParaEntregador(PratoFilter pratoFilter, Pageable pageable, Long codigo);
	
}
