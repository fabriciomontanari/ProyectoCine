package com.Cine.dao;

import com.Cine.domain.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeliculaDao 
        extends JpaRepository <Pelicula, Long>{
    
    //Consulta ampliada nativa
    public List<Pelicula> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    
    //Consulta ampliada tipo JPQL
    @Query(value="SELECT a FROM Pelicula a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Pelicula> metodoJPQL(@Param("precioInf") double precioInf,@Param("precioSup")double precioSup);
    
    //Consulta ampliada tipo SQL nativo
    @Query(nativeQuery = true,
            value="SELECT * FROM pelicula a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Pelicula> metodoNativo(@Param("precioInf") double precioInf,@Param("precioSup")double precioSup);
}