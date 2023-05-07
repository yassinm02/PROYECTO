package com.stockcontroll.service.Product;

import com.stockcontroll.data.ProductoDao;
import com.stockcontroll.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImp implements ProductService{

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> findByName(String name) {
        return productoDao.findByProductNameContaining(name);
    }

    @Override
    public List<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productoDao.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void deleteById(int id) {
        productoDao.deleteById(id);
    }

    @Override
    public boolean ProductExists(int id) {
        return productoDao.existsById(id);
    }
}
