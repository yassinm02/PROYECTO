package com.stockcontroll.repository;

import com.stockcontroll.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Page<Producto> findByNameContaining(String productName, Pageable pageable);
    boolean existsById(Integer integer);
    Producto findByCodBarrasIgnoreCase(String codBarras);
}
