package br.com.zup.mercadolivre.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;


public class OpiniaoForm {
	
	@NotNull @NotBlank
	private String titulo;
	
	@Positive
	private Integer nota;
	
	@NotNull @NotBlank
	private String descricao;

	public OpiniaoForm() {

	}

	public OpiniaoForm(String titulo, Integer nota, String descricao) {
		super();
		this.titulo = titulo;
		this.nota = nota;
		this.descricao = descricao;
	}


	public Opiniao toModel(Produto produtoSelecionado, Usuario usrConsumidor) {
		return new Opiniao(titulo, nota, descricao, usrConsumidor, produtoSelecionado);
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
