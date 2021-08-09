package br.com.zup.mercadolivre.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.model.Caracteristica;
import br.com.zup.mercadolivre.model.Produto;

public class NovaCaracteristicaForm {
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public NovaCaracteristicaForm(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica toModel(Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
