package com.tienda_l;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //esta clase es de configuración del sistema
public class ProjectConfig implements WebMvcConfigurer {

    @Bean //solamente una versión de un objeto
    public LocaleResolver localResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault()); //saber el idioma default del sistema
        slr.setLocaleAttributeName("session.current.locale"); //ubicación de donde estoy
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");

        return lci;
    }

    //@Override
    public void addInterceptor(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/index").setViewName("index");
        registro.addViewController("/login").setViewName("login");
        registro.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //devuelve una cadena de filtros de seguridad. en qué áreas cualquiera puede entrar
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index",
                        "/carrito/**", "/registro/**", "/js/**",
                        "/webjars/**")
                .permitAll()
                .requestMatchers("/producto/nuevo",
                        "/producto/modificar/**",
                        "/producto/eliminar/**",
                        "/producto/guardar/**",
                        "/categoria/nuevo",
                        "/categoria/modificar/**",
                        "/categoria/eliminar/**",
                        "/categoria/guardar/**",
                        "/producto/query1")
                .hasRole("ADMIN")
                .requestMatchers("/producto/listado", 
                        "/categoria/listado")
                .hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
        )
                .formLogin((form) -> form
                        .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll()); 
    //lo que va a hacer match con los siguientes patrones. todo lo que esté en la ruta de carrito (**). Para todos esos request se les dice que se permite a todo mundo
        return http.build(); //retorna las cadenas de seguridad
    }
    
    //este método crea usuarios en memoria 
    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("juan")
                .password("{noop}123")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        
        UserDetails vendedor = User.builder()
                .username("rebeca")
                .password("{noop}456")
                .roles("USER", "VENDEDOR")
                .build();
        
        UserDetails usuario = User.builder()
                .username("pedro")
                .password("{noop}789")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, vendedor, usuario);
    } //el {noop} es para que no lo codifique
    //esto se usa para no tener que incorporar la base de datos (es solo momentáneo)
}
