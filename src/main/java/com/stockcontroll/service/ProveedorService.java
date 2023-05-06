package com.stockcontroll.service;

import com.stockcontroll.model_POJO.Producto;
import com.stockcontroll.model_POJO.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<Proveedor> findAll();

    Optional<Proveedor> findById(int id);

    Proveedor save(Proveedor proveedor);

    void deleteById(int id);

}
