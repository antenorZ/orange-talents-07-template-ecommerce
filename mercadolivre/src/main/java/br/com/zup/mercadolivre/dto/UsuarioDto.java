package br.com.zup.mercadolivre.dto;

import java.time.LocalDateTime;

public class UsuarioDto {
	private String login;
	
//	private String senha;
	
	private LocalDateTime momentoCadastro;

	public UsuarioDto(String login, LocalDateTime momentoCadastro) {
		super();
		this.login = login;
//		this.senha = senha;
		this.momentoCadastro = momentoCadastro;
	}

	public String getLogin() {
		return login;
	}

	public LocalDateTime getMomentoCadastro() {
		return momentoCadastro;
	}
	
	
}
