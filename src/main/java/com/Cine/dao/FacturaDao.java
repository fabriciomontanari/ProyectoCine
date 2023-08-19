package com.Cine.dao;

import com.Cine.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao 
        extends JpaRepository <Factura, Long>{
    
}
