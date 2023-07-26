package com.Cine.service;

import com.Cine.domain.Pelicula;
import java.util.List;



public interface PeliculaService {
    
    //Se obtiene la lista de peliculas en un List
    public List<Pelicula> getPeliculas(boolean activo);
    
    // Se obtiene un pelicula, a partir del id de un pelicula
    public Pelicula getPelicula(Pelicula pelicula);
    
    // Se inserta un nuevo pelicula si el id del pelicula esta vacÃ­o
    // Se actualiza un pelicula si el id del pelicula NO esta vacÃ­o
    public void save(Pelicula pelicula);
    
    // Se elimina el pelicula que tiene el id pasado por parÃ¡metro
    public void delete(Pelicula pelicula);
    
//Lista de los peliculas que estan en un rango de precios y los ordena por descripcion.
    public List<Pelicula> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    
}
