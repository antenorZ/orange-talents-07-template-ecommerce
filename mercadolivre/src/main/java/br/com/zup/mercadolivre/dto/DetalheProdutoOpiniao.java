package br.com.zup.mercadolivre.dto;

import br.com.zup.mercadolivre.model.Opiniao;

public class DetalheProdutoOpiniao {
	private String titulo;
	
	private Integer nota;

	private String descricao;
	
	public DetalheProdutoOpiniao(Opiniao opiniao) {
		super();
		this.titulo = opiniao.getTitulo();
		this.nota = opiniao.getNota();
		this.descricao = opiniao.getDescricao();
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getNota() {
		return nota;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
