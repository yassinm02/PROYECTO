package com.stockcontroll.service.InventarioProducto;

import com.stockcontroll.model.InventarioProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventarioProductoService {

    List<InventarioProducto> obtenerPorIdInventario(int idInventario);

    Optional<InventarioProducto> obtenerPorIdInventarioYProducto(int idInventario, int idProducto);

    List<InventarioProducto> obtenerTodos();

    Page<InventarioProducto> obtenerTodosPaginado(Pageable pageable);

    InventarioProducto guardar(InventarioProducto inventarioProducto);

    Optional<InventarioProducto> actualizarRevisado(int idInventario, int idProducto, boolean revisado);

    void eliminar(InventarioProducto inventarioProducto);

    boolean existeInventarioProducto(int idInventario, int idProducto);

}
