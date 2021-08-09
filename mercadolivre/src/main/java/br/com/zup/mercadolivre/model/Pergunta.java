package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@ManyToOne
	private Usuario usrAutorPergunta;

	@ManyToOne
	private Produto produtoSelecionado;
	
	private LocalDateTime momentoCriacao = LocalDateTime.now();

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Pergunta() {
		super();
	}

	public Pergunta(String titulo, Usuario usrAutorPergunta, Produto produtoSelecionado) {
		super();
		this.titulo = titulo;
		this.usrAutorPergunta = usrAutorPergunta;
		this.produtoSelecionado = produtoSelecionado;
	}

	public Usuario getUsrAutorPergunta() {
		return usrAutorPergunta;
	}

	public void setUsrAutorPergunta(Usuario usrAutorPergunta) {
		this.usrAutorPergunta = usrAutorPergunta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
