package com.tienda_l;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //esta clase es de configuración del sistema
public class ProjectConfig implements WebMvcConfigurer{
    
    @Bean //solamente una versión de un objeto
    public LocaleResolver localResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault()); //saber el idioma default del sistema
        slr.setLocaleAttributeName("session.current.locale"); //ubicación de donde estoy
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        
        return lci;
    }
    
    //@Override
    public void addInterceptor(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
}
