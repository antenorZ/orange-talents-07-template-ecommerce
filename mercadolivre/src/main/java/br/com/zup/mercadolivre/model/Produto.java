package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.form.NovaCaracteristicaForm;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Double valor;
	
	private Integer qtd;
	
	private String descricao;
	
	@OneToOne
	private Categoria categoria;
	
	private LocalDateTime momentoCadastro = LocalDateTime.now();
	
	@ManyToOne
	private Usuario usrDono;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private List<Caracteristica> caracteristicas = new ArrayList<>();

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@OneToMany(mappedBy = "produtoSelecionado")
	private Set<Pergunta> perguntas = new HashSet<>();

	public Produto() {

	}

	public Usuario getUsrDono() {
		return usrDono;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(Set<ImagemProduto> imagens) {
		this.imagens = imagens;
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	public void setOpinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}

	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(Set<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setUsrDono(Usuario usrDono) {
		this.usrDono = usrDono;
	}

	public Produto(String nome, Double valor, Integer qtd, String descricao, Categoria categoria, List<NovaCaracteristicaForm> caracteristicas, Usuario usrDono) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtd = qtd;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toList()));
		this.usrDono = usrDono;
	}
	
	public ProdutoDto toDto() {
		return new ProdutoDto(id, nome);
	}

	public void associaImagens(Set<String> links) {
		links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelUsrDono) {
		return this.usrDono.equals(possivelUsrDono);
	}
	
	public <T>List<T> mapCaracteristicas(Function<Caracteristica, T> funcaoMapeadora){
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toList());
	}
	
	public <T> Set<T> mapLinksImagens(Function<ImagemProduto, T> funcaoMapeadora){
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora){
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapOpiniao(Function<Opiniao, T> funcaoMapeadora){
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public boolean abateEstoque(Integer qtdCompra) {
		if(qtdCompra <= this.qtd) {
			this.qtd-=qtdCompra;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(caracteristicas, categoria, descricao, id, imagens, momentoCadastro, nome, opinioes,
				perguntas, qtd, usrDono, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(caracteristicas, other.caracteristicas) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(imagens, other.imagens) && Objects.equals(momentoCadastro, other.momentoCadastro)
				&& Objects.equals(nome, other.nome) && Objects.equals(opinioes, other.opinioes)
				&& Objects.equals(perguntas, other.perguntas) && Objects.equals(qtd, other.qtd)
				&& Objects.equals(usrDono, other.usrDono) && Objects.equals(valor, other.valor);
	}
	
	
	
}
