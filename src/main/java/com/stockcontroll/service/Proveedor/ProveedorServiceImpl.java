package com.stockcontroll.service.Proveedor;

import com.stockcontroll.data_DAO.ProveedorDao;
import com.stockcontroll.model_POJO.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorDao proveedorDao;

    @Override
    public List<Proveedor> findAll() {
        return proveedorDao.findAll();
    }

    @Override
    public Optional<Proveedor> findById(int id) {
        return this.proveedorDao.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return this.proveedorDao.save(proveedor);
    }

    @Override
    public void deleteById(int id) {
        this.proveedorDao.deleteById(id);
    }
}
