package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Usuario;
import com.projetojpa.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description="API REST DE GERENCIAMENTO DE USUÁRIOS")
public class UsuarioController {
	private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    @Operation(summary = "Cadastra um usuário")
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid Usuario usuario) {
    	Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Alterar um usuário")
    public ResponseEntity<Usuario> alteraUsuariosControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
    	Usuario alteraUsuario = usuarioService.alterarUsuario(id, usuario);
    	if (alteraUsuario != null) {
    		return ResponseEntity.ok(usuario);
    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }
    @GetMapping("/{id}")
    @Operation(summary = "Localiza usuário por ID")
	public ResponseEntity<Usuario> buscaUsuariosControlId (@PathVariable Long id){
		Usuario usuario = usuarioService.buscaUsuarioById(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuários")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl(){
		List<Usuario> usuario = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(usuario);
	}

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar um usuário")
    public ResponseEntity<Usuario> apagarUsuarioControl(@PathVariable Long id){
    	boolean apagar = usuarioService.apagarUsuario(id);
    	if (apagar) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	else {
    		return ResponseEntity.notFound().build();    	}
    }

}
