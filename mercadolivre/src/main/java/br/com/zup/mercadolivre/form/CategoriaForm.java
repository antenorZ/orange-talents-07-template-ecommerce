package br.com.zup.mercadolivre.form;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.config.validation.Exists;
import br.com.zup.mercadolivre.config.validation.Unique;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

public class CategoriaForm {
	@NotNull @NotBlank @Unique(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
//	@Exists(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaMaeId;
	
	public Categoria toModel(CategoriaRepository categoriaRepository){
		if(categoriaMaeId == null) {
			return new Categoria(nome);
		}
		else {
			Categoria categoriaMae = categoriaRepository.getById(categoriaMaeId);
			return new Categoria(nome, categoriaMae);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public void setCategoriaMaeId(Long categoriaMaeId) {
		this.categoriaMaeId = categoriaMaeId;
	}

}
