package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private StatusTransacao status;
	@NotNull
	private String idTransacaoGateway;
	
	private LocalDateTime  instanteCriacao;
	
	@NotNull 
	@ManyToOne
	private Compra compraProcessada;

	public Transacao() {

	}

	public Transacao(@NotNull StatusTransacao status, @NotNull String idTransacaoGateway, Compra compraProcessada) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.instanteCriacao = LocalDateTime.now();
		this.compraProcessada = compraProcessada;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(compraProcessada, id, idTransacaoGateway, instanteCriacao, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(compraProcessada, other.compraProcessada) && Objects.equals(id, other.id)
				&& Objects.equals(idTransacaoGateway, other.idTransacaoGateway)
				&& Objects.equals(instanteCriacao, other.instanteCriacao) && status == other.status;
	}
	
	
}
