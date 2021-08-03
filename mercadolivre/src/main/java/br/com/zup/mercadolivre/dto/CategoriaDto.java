package br.com.zup.mercadolivre.dto;

public class CategoriaDto{
	private String nome;

	public CategoriaDto(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
