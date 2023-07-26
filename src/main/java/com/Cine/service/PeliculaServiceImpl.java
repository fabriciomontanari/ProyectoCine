package com.Cine.service;

import com.Cine.dao.PeliculaDao;
import com.Cine.domain.Pelicula;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

       //Se crea un objeto tipo Autowired que se crearà automàticamente y solo una vez
    //Se crea un objeto tipo Autowired que se crearà automàticamente y solo una vez
    @Autowired
    private PeliculaDao peliculaDao;

    @Override
    @Transactional(readOnly=true)
    public List<Pelicula> getPeliculas(boolean activo) {
        var lista = peliculaDao.findAll();
        if (activo) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
     @Override
    @Transactional(readOnly = true)
    public Pelicula getPelicula(Pelicula pelicula) {
        return peliculaDao.findById(pelicula.getIdPelicula()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Pelicula pelicula) {
        peliculaDao.save(pelicula);
    }

    @Override
    @Transactional
    public void delete(Pelicula pelicula) {
        peliculaDao.delete(pelicula);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Pelicula> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup){
        return peliculaDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup); 
    }
    
}
