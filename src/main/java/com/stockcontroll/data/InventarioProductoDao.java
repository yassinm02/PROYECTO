package com.stockcontroll.data;

import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.InventarioProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventarioProductoDao extends JpaRepository<InventarioProducto, InventarioProductoId> {
    List<InventarioProducto> findByInventarioId(int idInventario);


}