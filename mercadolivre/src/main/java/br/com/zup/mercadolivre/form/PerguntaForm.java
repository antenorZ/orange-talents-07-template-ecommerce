package br.com.zup.mercadolivre.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class PerguntaForm {
	@NotNull @NotBlank
	private String titulo;

	public PerguntaForm(@NotNull @NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}
	
	public Pergunta toModel(Usuario usrAutorPergunta, Produto produtoSelecionado) {
		return new Pergunta(titulo, usrAutorPergunta, produtoSelecionado);
	}

	public PerguntaForm() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
