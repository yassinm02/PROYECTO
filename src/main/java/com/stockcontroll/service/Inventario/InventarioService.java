package com.stockcontroll.service.Inventario;

import com.stockcontroll.model.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {

    List<Inventario> obtenerTodos();
    Optional<Inventario> obtenerPorId(int id);
    Inventario guardar(Inventario inventario);
    void eliminar(int id);


}
