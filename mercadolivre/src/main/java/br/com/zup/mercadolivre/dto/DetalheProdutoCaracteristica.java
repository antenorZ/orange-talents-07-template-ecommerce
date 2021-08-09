package br.com.zup.mercadolivre.dto;

import br.com.zup.mercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristica {
	
	private String nome;
	
	private String descricao;
	
	public DetalheProdutoCaracteristica(Caracteristica caracteristica){
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
