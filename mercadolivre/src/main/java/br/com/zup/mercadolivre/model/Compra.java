package br.com.zup.mercadolivre.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.form.RetornoPagSeguroForm;
import io.jsonwebtoken.lang.Assert;

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
	
	@OneToMany(mappedBy = "compraProcessada", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

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
	
	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento retornoGatewayPagamento) {
		Transacao novaTransacao = retornoGatewayPagamento.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe transacao igual");
		Set<Transacao> transacoesConcluidasSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
		Assert.isTrue(transacoesConcluidasSucesso.isEmpty(), "Essa compra já foi concluída com sucesso");
		this.transacoes.add(retornoGatewayPagamento.toTransacao(this));
		
	}

	
	
}
