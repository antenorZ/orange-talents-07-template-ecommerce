package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usrComprador;
	
	@ManyToOne
	private Produto produtoComprado;
	
	private Integer qtdCompra;
	
	@Enumerated
	private GatewayPagamento gateway;

	public Compra() {

	}
	
	public Long getId() {
		return id;
	}
	
	public Compra(Usuario usrComprador, Produto produtoComprado, Integer qtdCompra, @NotNull GatewayPagamento gateway) {
		super();
		this.usrComprador = usrComprador;
		this.produtoComprado = produtoComprado;
		this.qtdCompra = qtdCompra;
		this.gateway = gateway;
	}

	public Usuario getUsrComprador() {
		return usrComprador;
	}

	public Produto getProdutoComprado() {
		return produtoComprado;
	}

	public Integer getQtdCompra() {
		return qtdCompra;
	}
}
