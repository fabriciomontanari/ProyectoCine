package com.Cine.dao;

import com.Cine.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao 
        extends JpaRepository <Venta, Long>{
    
}
