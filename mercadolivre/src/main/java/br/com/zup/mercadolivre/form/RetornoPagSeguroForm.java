package br.com.zup.mercadolivre.form;

import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.model.StatusRetornoGateway;
import br.com.zup.mercadolivre.model.Transacao;

public class RetornoPagSeguroForm implements RetornoGatewayPagamento{
	@NotNull
	private String idTransacao;
	private StatusRetornoGateway status;
	
	public RetornoPagSeguroForm(@NotNull String idTransacao, StatusRetornoGateway status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetornoGateway getStatus() {
		return status;
	}
	
	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
}
