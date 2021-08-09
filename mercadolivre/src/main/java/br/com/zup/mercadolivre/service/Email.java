package br.com.zup.mercadolivre.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Pergunta;

@Service
public class Email {
	
	@Autowired
	private Mailer mailer;
	
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("Mensagem", "Titulo", pergunta.getUsrAutorPergunta().getLogin(), pergunta.getProdutoSelecionado().getUsrDono());
	}
}