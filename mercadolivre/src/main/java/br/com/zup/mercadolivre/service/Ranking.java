package br.com.zup.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Compra;

@Service
public class Ranking {
	
	@Autowired
	AdicionaAoRanking addRanking;
	
	public void addNoRanking(Compra compraProcessada) {
		addRanking.adiciona(compraProcessada.getId(), compraProcessada.getProdutoComprado().getUsrDono().getId());
	}
}
