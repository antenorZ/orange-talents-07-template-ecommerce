package br.com.zup.mercadolivre.service;

import br.com.zup.mercadolivre.model.Usuario;

public interface Mailer {

	void send(String corpoEmail, String titulo, String usrAutorPergunta, Usuario usrDonoProduto);
}
