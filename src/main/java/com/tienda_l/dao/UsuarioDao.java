package com.tienda_l.dao;

import com.tienda_l.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
