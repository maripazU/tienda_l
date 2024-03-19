package com.tienda_l.service.impl;

import com.tienda_l.dao.UsuarioDao;
import com.tienda_l.domain.Rol;
import com.tienda_l.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl 
        implements UsuarioDetailsService, UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private HttpSession session; //para guardar una variable de sesión, lo que va es la ruta de la imagen del usuario
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //Se busca el usuario por el username
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //se valida si el usuario se encontró
        if (usuario == null) { //no lo encontró
            throw new UsernameNotFoundException(username);
        }
        //Sí se encontró el usuario... se actualiza la foto de la variable
        session.removeAttribute("usuarioImagen");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen()); //se coloca la imagen del usuario en algún lugar (??)
    
        //se deben recuperar los roles del usuario... y se crearlos como roles 
        var roles = new ArrayList<GrantedAuthority>(); //los roles en la base de datos son solo string entonces se incluyen en un arreglo de permisos
        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        //ya se tiene el arrglo con los roles ya reales...
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
    //en resumen este método busca un usuario obtiene los roles de un usuario y los tranforma en roles de seguridad. 
    
}
