package com.stockcontroll.service.Product;

import com.stockcontroll.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Producto> findByName(String name);
    List<Producto> findAll();

    Optional<Producto> findById(int id);

    Producto save(Producto producto);

    public void deleteById(int id);

    boolean ProductExists(int id);
}
