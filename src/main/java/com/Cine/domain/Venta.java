package com.Cine.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="venta")
public class Venta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long idVenta;
    private Long idFactura;
    private Long idPelicula;
    private double precio;
    private int cantidad;

    public Venta(){
        
    }

    public Venta(Long idFactura, Long idPelicula, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idPelicula = idPelicula;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    
}
