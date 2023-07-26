
package com.Cine.service;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);
    
    //El BuketName es el <id del proyecto> +.appspot.com
    final String BucketName="tiendaentradas.appspot.com";
    
    //Esta es la ruta base de este proyecto en el storage
    final String rutaSuperiorStorage="tiendaentradas";
    
    //Ubicacion del achivo de configuracion Json
    final String rutaJsonFile="firebase";
    
    final String archivoJsonFile="tiendaentradas-firebase-adminsdk-ox5e5"; 
}
