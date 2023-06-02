package com.stockcontroll.service.Product;

import com.stockcontroll.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Producto> findByName(String name, Pageable pageable);
    List<Producto> findAll();
    Page<Producto> findAll(Pageable pageable);
    List<Producto> findAllPags(int page,int size);

    Optional<Producto> findById(int id);

    Producto save(Producto producto);

    public void deleteById(int id);

    boolean ProductExistsById(int id);

    Producto findByCodBarras(String codBarras);

    long count();
}
