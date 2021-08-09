package br.com.zup.mercadolivre.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

public class ProdutoForm {
	
	@NotNull @NotBlank
	private String nome;
	
	@NotNull @Positive
	private Double valor;
	
	@NotNull @Min(value = 1)
	private Integer qtd;
	
	@Length(max = 1000)
	private String descricao;
	
	@NotNull 
	private Long idCategoria;
	
	@Size(min = 3)
	private List<NovaCaracteristicaForm> caracteristicas = new ArrayList<>();
	
	public ProdutoForm(@NotNull @NotBlank String nome, @NotNull @Positive Double valor, @NotNull @Min(1) Integer qtd,
			@Length(max = 1000) String descricao, @NotNull Long idCategoria,
			@Size(min = 3) List<NovaCaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtd = qtd;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usrDono){
		Categoria categoria = categoriaRepository.getById(idCategoria);
		return new Produto(nome, valor, qtd, descricao, categoria, caracteristicas, usrDono);
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	
	
}
