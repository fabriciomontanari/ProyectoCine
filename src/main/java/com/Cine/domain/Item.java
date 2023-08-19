package com.Cine.domain;

import lombok.Data;

@Data
public class Item extends Pelicula {
    
    private int cantidad;
    
    public Item(){
        
    }
    
    public Item(Pelicula pelicula) {
        super.setActivo(pelicula.isActivo());
        super.setCategoria(pelicula.getCategoria());
        super.setDescripcion(pelicula.getDescripcion());
        super.setDetalle(pelicula.getDetalle());
        super.setExistencias(pelicula.getExistencias());
        super.setIdPelicula(pelicula.getIdPelicula());
        super.setPrecio(pelicula.getPrecio());
        super.setRutaImagen(pelicula.getRutaImagen());
        this.cantidad=0;
    }
    
}
