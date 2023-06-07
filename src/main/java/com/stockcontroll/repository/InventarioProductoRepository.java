package com.stockcontroll.repository;

import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.InventarioProductoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioProductoRepository extends JpaRepository<InventarioProducto, InventarioProductoId> {
    List<InventarioProducto> findByInventarioId(int idInventario);

    Page<InventarioProducto> findByInventarioId(int idInventario, Pageable pageable);


    @Modifying
    @Query("UPDATE InventarioProducto ip SET ip.producto = null WHERE ip.producto.id = :productId")
    void updateProductToNull(int productId);
}