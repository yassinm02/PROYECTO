package com.stockcontroll.service.Product;

import com.stockcontroll.repository.ProductoRepository;
import com.stockcontroll.model.Producto;
import com.stockcontroll.service.Proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImp implements ProductService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Override
    public Page<Producto> findByName(String name, Pageable pageable) {
        return productoRepository.findByNameContaining(name, pageable);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<Producto> findAllPags(int page, int size) {
        Pageable pages = PageRequest.of(page, size);
        Page<Producto> productoPage = productoRepository.findAll(pages);

        return productoPage.getContent();
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public boolean ProductExistsById(int id) {
        return productoRepository.existsById(id);
    }

    @Override
    public Producto findByCodBarras(String codBarras) {
        return productoRepository.findByCodBarrasIgnoreCase(codBarras);
    }
    @Override
    public long count(){
        return productoRepository.count();
    }
}
