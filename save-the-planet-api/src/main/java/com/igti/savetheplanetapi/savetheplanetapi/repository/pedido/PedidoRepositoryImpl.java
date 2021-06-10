package com.igti.savetheplanetapi.savetheplanetapi.repository.pedido;

import com.igti.savetheplanetapi.savetheplanetapi.model.*;
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
import java.util.Arrays;
import java.util.List;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pedido> resumirPedidosEntregador(Pedido pedido, Pageable pageable, Long codigo) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        criteria.select(root);

        Predicate[] predicates = criarRestricoesEntregador(pedido, builder, root, codigo);
        criteria.where(predicates);

        TypedQuery<Pedido> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, totalEntregador(pedido, codigo));
    }

    @Override
    public Page<Pedido> resumirPedidosCozinheira(Pedido pedido, Pageable pageable, Long codigo) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Prato> criteria = builder.createQuery(Prato.class);
        Root<Prato> root = criteria.from(Prato.class);
        criteria.select(root);
        TypedQuery<Prato> query = manager.createQuery(criteria);
        List<Prato> pratos = query.getResultList();
        List<Long> codigos = new ArrayList<>();
        for(Prato prato : pratos){
            codigos.add(prato.getCodigo());
        }

        CriteriaQuery<Pedido> criteriaPedido = builder.createQuery(Pedido.class);
        Root<Pedido> rootPedido = criteriaPedido.from(Pedido.class);

        criteriaPedido.select(rootPedido);

        TypedQuery<Pedido> queryPedido = manager.createQuery(criteriaPedido);
        List<Pedido> pedidos = queryPedido.getResultList();
        List<Pedido> pedidosCozinheira = new ArrayList<>();
        for (Pedido pedido1 : pedidos){
            if (!pedido1.getPratos().isEmpty()) {
                if(codigos.contains(pedido1.getPratos().get(0).getCodigo())){
                    pedidosCozinheira.add(pedido1);
                    continue;
                }
            }
        }
        adicionarRestricoesDePaginacao(queryPedido, pageable);

        return new PageImpl<>(pedidosCozinheira, pageable, pedidosCozinheira.size());
    }

    @Override
    public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, Long codigo) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        criteria.select(root);

        Predicate[] predicates = criarRestricoes(pedido, builder, root, codigo);
        criteria.where(predicates);

        TypedQuery<Pedido> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(pedido, codigo));
    }

    private Predicate[] criarRestricoes(Pedido pedido, CriteriaBuilder builder, Root<Pedido> root, Long codigo) {
        List<Predicate> predicates = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setCodigo(codigo);
        predicates.add(builder.equal(root.get(Pedido_.cliente), usuario));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Predicate[] criarRestricoesEntregador(Pedido pedido, CriteriaBuilder builder, Root<Pedido> root, Long codigo) {
        List<Predicate> predicates = new ArrayList<>();

        Predicate statusPronto = builder.equal(root.get(Pedido_.status), "PRONTO");
        Usuario usuario = new Usuario();
        usuario.setCodigo(codigo);
		Predicate codigoEntregador = builder.equal(root.get(Pedido_.entregador), usuario);
		predicates.add(builder.or(statusPronto, codigoEntregador));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(Pedido pedido, Long codigo) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        Predicate[] predicates = criarRestricoes(pedido, builder, root, codigo);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Long totalEntregador(Pedido pedido, Long codigo) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        Predicate[] predicates = criarRestricoesEntregador(pedido, builder, root, codigo);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
