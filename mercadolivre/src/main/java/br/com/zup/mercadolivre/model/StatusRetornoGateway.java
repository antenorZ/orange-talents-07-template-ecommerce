package br.com.zup.mercadolivre.model;

public enum StatusRetornoGateway {
	SUCESSO,
	ERRO;
	
	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		return StatusTransacao.erro;
	}
}
