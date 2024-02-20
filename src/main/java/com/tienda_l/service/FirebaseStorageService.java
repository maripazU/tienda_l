package com.tienda_l.service;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    //método con tres atributos, nombre dle archivo, la capeta donde esta y un identificador de la imagen que vamos a guardar
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "tecshop-l.appspot.com";

    //Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "techshop";
    //para decir que en el storage vamos a tener una capeta que se va a llamar techshop

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    //nombre con el que se crea el folder que se hizo previamente de firebase 

    //El nombre del archivo Json
    final String archivoJsonFile = "tecshop-l-firebase-adminsdk-z6pjg-9cd172aba6.json";
    //nombre del archivo de la clave. se pone con todo y .json -- para ver como la aplicación se va a comunicar con el firebase
}
