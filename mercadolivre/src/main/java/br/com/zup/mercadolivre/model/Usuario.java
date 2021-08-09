package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Past;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import br.com.zup.mercadolivre.config.validation.SenhaLimpa;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.mercadolivre.dto.UsuarioDto;

@Entity
public class Usuario implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login;
		
	private String senha;
	
	public Long getId() {
		return id;
	}

	@Past
	private LocalDateTime momentoCadastro = LocalDateTime.now();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Usuario() {
		super();
	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	public UsuarioDto toDto() {
		return new UsuarioDto(login, momentoCadastro);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, momentoCadastro, perfis, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(momentoCadastro, other.momentoCadastro) && Objects.equals(perfis, other.perfis)
				&& Objects.equals(senha, other.senha);
	}
	
	
}
