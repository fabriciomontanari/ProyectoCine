package com.Cine.controller;

import com.Cine.domain.Pelicula;
import com.Cine.service.CategoriaService;
import com.Cine.service.FirebaseStorageServiceImpl;
import com.Cine.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/pelicula")
public class PeliculaController {
    
    @Autowired
    private PeliculaService peliculaService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var peliculas = peliculaService.getPeliculas(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalPeliculas", peliculas.size());
        return "/pelicula/listado";
    }
    
    @GetMapping("/nuevo")
    public String peliculaNuevo(Pelicula pelicula){
        return "/pelicula/modifica";
    }
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String peliculaGuardar(Pelicula pelicula, @RequestParam("imagenFile") MultipartFile imagenFile){
        if(!imagenFile.isEmpty()){
            peliculaService.save(pelicula);
            pelicula.setRutaImagen(firebaseStorageService.cargaImagen(imagenFile, "pelicula", pelicula.getIdPelicula()));
        }
        peliculaService.save(pelicula);
        return "redirect:/pelicula/listado";
    }
    
    @GetMapping("/eliminar/{idPelicula}")
    public String peliculaEliminar(Pelicula pelicula){
        peliculaService.delete(pelicula);
        return "redirect:/pelicula/listado";
    }
    
    @GetMapping("/modificar/{idPelicula}")
    public String peliculaEliminar(Pelicula pelicula, Model model){
        pelicula = peliculaService.getPelicula(pelicula);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("categorias",categorias);
        return "/pelicula/modifica";
    }
}
