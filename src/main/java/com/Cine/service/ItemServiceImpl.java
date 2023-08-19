
package com.Cine.service;
import com.Cine.dao.FacturaDao;
import com.Cine.dao.VentaDao;
import com.Cine.domain.Pelicula;
import com.Cine.domain.Usuario;
import com.Cine.domain.Factura;
import com.Cine.domain.Item;
import com.Cine.domain.Venta;
import com.Cine.service.UsuarioService;
import com.Cine.service.ItemService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.Cine.dao.PeliculaDao;
 
@Service
public class ItemServiceImpl implements ItemService {
 
    @Override
    public List<Item> gets() {
        return listaItems;
    }
 
    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el pelicula en el carrito
            if (Objects.equals(i.getIdPelicula(), item.getIdPelicula())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el pelicula en el carrito se agrega cantidad =1.            
            item.setCantidad(1);
            listaItems.add(item);
        }
    }
 
    //Se usa para eliminar un pelicula del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdPelicula(), item.getIdPelicula())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }
 
    //Se obtiene la información de un pelicula del carrito... para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdPelicula(), item.getIdPelicula())) {
                return i;
            }
        }
        return null;
    }
 
    //Se usa en la página para actualizar la cantidad de peliculas
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdPelicula(), item.getIdPelicula())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }
 
    @Autowired
    private UsuarioService uuarioService;
 
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private PeliculaDao peliculaDao;
 
    @Override
    public void facturar() {
        System.out.println("Facturando");
 
        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
 
        if (username.isBlank()) {
            return;
        }
 
        Usuario usuario = uuarioService.getUsuarioPorUsername(username);
 
        if (usuario == null) {
            return;
        }
 
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);
 
        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Pelicula: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdPelicula(), i.getPrecio(), i.getCantidad());
            ventaDao.save(venta);
            Pelicula pelicula = peliculaDao.getReferenceById(i.getIdPelicula());
            pelicula.setExistencias(pelicula.getExistencias()-i.getCantidad());
            peliculaDao.save(pelicula);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}