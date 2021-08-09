package br.com.zup.mercadolivre.dto;

public class ProdutoDto {
	private Long id;
	private String nome;
	
	public ProdutoDto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
