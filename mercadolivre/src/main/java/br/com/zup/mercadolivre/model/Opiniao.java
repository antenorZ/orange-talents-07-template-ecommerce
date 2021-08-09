package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Opiniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private Integer nota;
	
	private String descricao;
	
	@ManyToOne
	private Usuario consumidor;
	
	@ManyToOne
	private Produto produto;

	public Opiniao() {

	}
	
	public Opiniao(String titulo, Integer nota, String descricao, Usuario consumidor, Produto produto) {
		this.titulo = titulo;
		this.nota = nota;
		this.descricao = descricao;
		this.consumidor = consumidor;
		this.produto = produto;
	}

	public Long getId() {
		return id;
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

	public Usuario getConsumidor() {
		return consumidor;
	}

	public Produto getProduto() {
		return produto;
	}

	
	
}
