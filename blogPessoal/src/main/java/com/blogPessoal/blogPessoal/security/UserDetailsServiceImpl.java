package com.blogPessoal.blogPessoal.security;

import com.blogPessoal.blogPessoal.model.Usuario;
import com.blogPessoal.blogPessoal.repository.UsuarioRepository;
import com.blogPessoal.blogPessoal.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

        if (usuario.isPresent())
            return new UserDetailsImpl(usuario.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}
