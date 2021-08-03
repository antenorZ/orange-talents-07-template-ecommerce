package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import br.com.zup.mercadolivre.config.validation.SenhaLimpa;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.mercadolivre.dto.UsuarioDto;

@Entity
public class Usuario{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login;
		
	private String senha;
	
	@Past
	private LocalDateTime momentoCadastro = LocalDateTime.now();
	
//	private List<Perfil> perfis = new ArrayList<>();

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	public UsuarioDto toDto() {
		return new UsuarioDto(login, momentoCadastro);
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.perfis;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.senha;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.login;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
}
