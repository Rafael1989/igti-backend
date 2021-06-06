package com.igti.savetheplanetapi.savetheplanetapi.repository.venda;

import com.igti.savetheplanetapi.savetheplanetapi.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositoryImpl implements VendaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
		Root<Venda> root = criteria.from(Venda.class);
		
		criteria.select(root);
		
		Predicate[] predicates = criarRestricoes(venda, builder, root, codigo);
		criteria.where(predicates);
		
		TypedQuery<Venda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(venda, codigo));
	}

	@Override
	public Page<Venda> resumirVendasCozinheira(Venda venda, Pageable pageable, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
		Root<Venda> root = criteria.from(Venda.class);

		criteria.select(root);

		Predicate[] predicates = criarRestricoesCozinheira(venda, builder, root, codigo);
		criteria.where(predicates);

		TypedQuery<Venda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, totalCozinheira(venda, codigo));
	}

	private Predicate[] criarRestricoes(Venda venda, CriteriaBuilder builder,	Root<Venda> root, Long codigo) {
		List<Predicate> predicates = new ArrayList<>();

		Usuario usuario = new Usuario();
		usuario.setCodigo(codigo);
		predicates.add(builder.equal(root.get(Venda_.cliente), usuario));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Predicate[] criarRestricoesCozinheira(Venda venda, CriteriaBuilder builder,	Root<Venda> root, Long codigo) {
		List<Predicate> predicates = new ArrayList<>();

		Usuario usuario = new Usuario();
		usuario.setCodigo(codigo);
		predicates.add(builder.equal(root.get(Venda_.cozinheira), usuario));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(Venda venda, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Venda> root = criteria.from(Venda.class);
		
		Predicate[] predicates = criarRestricoes(venda, builder, root, codigo);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Long totalCozinheira(Venda venda, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Venda> root = criteria.from(Venda.class);

		Predicate[] predicates = criarRestricoesCozinheira(venda, builder, root, codigo);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
