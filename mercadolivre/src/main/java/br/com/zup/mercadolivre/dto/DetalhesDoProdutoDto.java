package br.com.zup.mercadolivre.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
//import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;

import br.com.zup.mercadolivre.model.Produto;

public class DetalhesDoProdutoDto {
	private String nomeProduto;
	
	private Double precoProduto;
	
	private String descricaoProduto;
	
	private List<DetalheProdutoCaracteristica> caracteristicasProduto = new ArrayList<>();

	private Set<String> linkImagens = new HashSet<>();
	
	private Set<String> tituloPerguntas = new HashSet<>();
	
	private Set<DetalheProdutoOpiniao> opinioesProduto = new HashSet<>();
	
	private Double mediaOpinioes;
	
	private Integer totalNotas;
	
	public DetalhesDoProdutoDto() {
		
	}

	public DetalhesDoProdutoDto(Produto produto) {
		this.nomeProduto = produto.getNome();
		this.precoProduto = produto.getValor();
		this.descricaoProduto = produto.getDescricao();
		this.caracteristicasProduto = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linkImagens = produto.mapLinksImagens(imagem -> imagem.getLink());
		this.tituloPerguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioesProduto = produto.mapOpiniao(DetalheProdutoOpiniao::new);
		Set<Integer> notas = produto.mapOpiniao(opiniao -> opiniao.getNota());
		this.totalNotas = notas.size();
		IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
		OptionalDouble average = mapToInt.average();
		if(average.isPresent()) {
			this.mediaOpinioes = average.getAsDouble();
		}
	}
	
	

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public List<DetalheProdutoCaracteristica> getCaracteristicasProduto() {
		return caracteristicasProduto;
	}

	public Set<String> getLinkImagens() {
		return linkImagens;
	}

	public Set<String> getTituloPerguntas() {
		return tituloPerguntas;
	}

	public Set<DetalheProdutoOpiniao> getOpinioesProduto() {
		return opinioesProduto;
	}

	public Double getMediaOpinioes() {
		return mediaOpinioes;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}
	
	
}
