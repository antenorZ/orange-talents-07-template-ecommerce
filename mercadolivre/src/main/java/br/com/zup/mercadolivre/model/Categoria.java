package br.com.zup.mercadolivre.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.mercadolivre.dto.CategoriaDto;

@Entity
public class Categoria{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;

	public Categoria() {
		super();
	}

	public Categoria(String nome){
		this.nome = nome;
	}

	public Categoria(String nome, Categoria categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public CategoriaDto toDto() {
		return new CategoriaDto(nome);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoriaMae, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(categoriaMae, other.categoriaMae) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
	
}
