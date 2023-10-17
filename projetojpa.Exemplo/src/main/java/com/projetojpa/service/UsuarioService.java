package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Usuario;
import com.projetojpa.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
	public List<Usuario> buscaTodosUsuarios(){
		return usuarioRepository.findAll();
	}

    public Usuario buscaUsuarioById(Long id) {
    	Optional <Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }
    
    public Usuario salvaUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Long id, Usuario alterarUser) {
    	Optional <Usuario> existeUsuario = usuarioRepository.findById(id);
    	if(existeUsuario.isPresent()) {
    		alterarUser.setId(id);
    		return usuarioRepository.save(alterarUser);
    	}
    	return null;
    }
    public boolean apagarUsuario(Long id) {
    	Optional <Usuario> existeUsuario = usuarioRepository.findById(id);
    	if(existeUsuario.isPresent()) {
    		usuarioRepository.deleteById(id);
    		return true;
    	}
    	return false;
    }
}
