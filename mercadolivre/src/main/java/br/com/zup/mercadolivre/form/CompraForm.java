package br.com.zup.mercadolivre.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.config.validation.Exists;
import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.ProdutoRepository;

public class CompraForm {
	
	@NotNull @Exists(domainClass = Produto.class, fieldName = "id")
	private Long idProdutoSelecionado;
	
	@Positive
	private Integer qtdProdutoSelecionado;
	
	@NotNull
	private GatewayPagamento gateway;

	public Long getIdProdutoSelecionado() {
		return idProdutoSelecionado;
	}

	public Integer getQtdProdutoSelecionado() {
		return qtdProdutoSelecionado;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public CompraForm(@NotNull Long idProdutoSelecionado, @Positive Integer qtdProdutoSelecionado, @NotNull GatewayPagamento gateway) {
		super();
		this.idProdutoSelecionado = idProdutoSelecionado;
		this.qtdProdutoSelecionado = qtdProdutoSelecionado;
		this.gateway = gateway;
	}
	
	public Compra toModel(Produto produtoSelecionado, Usuario usrComprador, GatewayPagamento gateway) {
		return new Compra(usrComprador, produtoSelecionado, qtdProdutoSelecionado, gateway);
	}
}
