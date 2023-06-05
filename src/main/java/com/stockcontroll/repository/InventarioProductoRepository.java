package com.stockcontroll.repository;

import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.InventarioProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventarioProductoRepository extends JpaRepository<InventarioProducto, InventarioProductoId> {
    List<InventarioProducto> findByInventarioId(int idInventario);


}