package com.stockcontroll.service;

import com.stockcontroll.model_POJO.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Producto> findAll();

    Optional<Producto> findById(int id);

    Producto save(Producto producto);

    public void deleteById(int id);
}
