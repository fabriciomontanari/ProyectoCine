
package com.Cine.dao;

import com.Cine.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao 
        extends JpaRepository <Categoria, Long>{
    
}
