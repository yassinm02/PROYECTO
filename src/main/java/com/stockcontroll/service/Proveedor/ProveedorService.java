package com.stockcontroll.service.Proveedor;

import com.stockcontroll.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<Proveedor> findAll();

    Optional<Proveedor> findById(int id);

    Proveedor save(Proveedor proveedor);

    void deleteById(int id);

}
