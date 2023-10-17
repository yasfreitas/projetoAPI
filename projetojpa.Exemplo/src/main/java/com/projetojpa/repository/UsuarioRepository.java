package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
