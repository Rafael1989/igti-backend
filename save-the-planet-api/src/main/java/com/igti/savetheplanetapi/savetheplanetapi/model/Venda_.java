package com.igti.savetheplanetapi.savetheplanetapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venda.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, Long> codigo;
	public static volatile SingularAttribute<Venda, Pedido> pedido;
	public static volatile SingularAttribute<Venda, Prato> prato;
	public static volatile SingularAttribute<Venda, String> quantidade;
	public static volatile SingularAttribute<Venda, BigDecimal> valor;
	public static volatile SingularAttribute<Venda, Usuario> cliente;
	public static volatile SingularAttribute<Venda, Usuario> entregador;
	public static volatile SingularAttribute<Venda, Usuario> cozinheira;

}

