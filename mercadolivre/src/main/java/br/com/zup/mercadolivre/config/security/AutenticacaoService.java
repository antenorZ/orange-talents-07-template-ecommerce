package br.com.zup.mercadolivre.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findBylogin(username);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		else {
			throw new UsernameNotFoundException("Dados inválidos");
		}
}
	
}
