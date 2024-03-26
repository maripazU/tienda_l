package com.tienda_l.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioDetailsService {
    
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException; //esto se tiene que llamar así porque así se llama en la cosa de seguridad
    //el user details se llaa así en la configuración del proyecto. Esto es para usar las bases de datos en lugar de eso 
}
