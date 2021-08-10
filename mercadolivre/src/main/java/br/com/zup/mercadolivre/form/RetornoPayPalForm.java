package br.com.zup.mercadolivre.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.model.StatusTransacao;
import br.com.zup.mercadolivre.model.Transacao;

public class RetornoPayPalForm implements RetornoGatewayPagamento{
	@Min(0)
	@Max(1)
	private Integer status;
	
	@NotBlank
	private String idTransacao;

	public RetornoPayPalForm(@Min(0) @Max(1) Integer status, @NotBlank String idTransacao) {
		this.status = status;
		this.idTransacao = idTransacao;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
		return new Transacao(statusCalculado, idTransacao, compra);
	}
	
}
