package com.igti.savetheplanetapi.savetheplanetapi.repository.prato;

import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato_;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PratoRepositoryImpl implements PratoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Prato> filtrar(PratoFilter pratoFilter, Pageable pageable, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Prato> criteria = builder.createQuery(Prato.class);
		Root<Prato> root = criteria.from(Prato.class);
		
		Predicate[] predicates = criarRestricoes(pratoFilter, builder, root, codigo);
		criteria.where(predicates);
		
		TypedQuery<Prato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pratoFilter, codigo));
	}
	

	@Override
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPrato> criteria = builder.createQuery(ResumoPrato.class);
		Root<Prato> root = criteria.from(Prato.class);
		
		criteria.select(builder.construct(ResumoPrato.class
				, root.get(Prato_.codigo), root.get(Prato_.descricao)
				, root.get(Prato_.valor), root.get(Prato_.quantidade)));
		
		Predicate[] predicates = criarRestricoes(pratoFilter, builder, root, codigo);
		criteria.where(predicates);
		
		TypedQuery<ResumoPrato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pratoFilter, codigo));
	}

	@Override
	public Page<ResumoPrato> resumir(PratoFilter pratoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPrato> criteria = builder.createQuery(ResumoPrato.class);
		Root<Prato> root = criteria.from(Prato.class);

		criteria.select(builder.construct(ResumoPrato.class
				, root.get(Prato_.codigo), root.get(Prato_.descricao)
				, root.get(Prato_.valor), root.get(Prato_.quantidade)));

		Predicate[] predicates = criarRestricoes(pratoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPrato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pratoFilter));
	}

	private Predicate[] criarRestricoes(PratoFilter pratoFilter, CriteriaBuilder builder,
										Root<Prato> root, Long codigo) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(root.get(Prato_.cozinheira), codigo));
		if (!StringUtils.isEmpty(pratoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Prato_.descricao)), "%" + pratoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Predicate[] criarRestricoes(PratoFilter pratoFilter, CriteriaBuilder builder,
										Root<Prato> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(pratoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Prato_.descricao)), "%" + pratoFilter.getDescricao().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(PratoFilter pratoFilter, Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Prato> root = criteria.from(Prato.class);
		
		Predicate[] predicates = criarRestricoes(pratoFilter, builder, root, codigo);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Long total(PratoFilter pratoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Prato> root = criteria.from(Prato.class);

		Predicate[] predicates = criarRestricoes(pratoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
