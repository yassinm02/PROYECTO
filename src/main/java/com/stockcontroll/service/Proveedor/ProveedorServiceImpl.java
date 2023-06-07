package com.stockcontroll.service.Proveedor;

import com.stockcontroll.repository.ProveedorRepository;
import com.stockcontroll.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> findById(int id) {
        return this.proveedorRepository.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return this.proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(int id) {
        this.proveedorRepository.deleteById(id);
    }

    @Override
    public boolean ProveedorExists(int id) {
        return proveedorRepository.existsById(id);
    }
}
