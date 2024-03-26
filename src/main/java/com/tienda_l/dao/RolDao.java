package com.tienda_l.dao;

import com.tienda_l.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository <Rol, Long>{ 
    
}
