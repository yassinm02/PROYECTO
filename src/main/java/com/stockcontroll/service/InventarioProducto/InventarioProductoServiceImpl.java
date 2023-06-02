package com.stockcontroll.service.InventarioProducto;


import com.stockcontroll.data.InventarioProductoDao;
import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.InventarioProductoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioProductoServiceImpl implements InventarioProductoService{

    @Autowired
    private InventarioProductoDao inventarioProductoDao;

    public List<InventarioProducto> obtenerPorIdInventario(int idInventario) {
        return inventarioProductoDao.findByInventarioId(idInventario);
    }


    @Override
    public Optional<InventarioProducto> obtenerPorIdInventarioYProducto(int idInventario, int idProducto) {
        return Optional.empty();
    }

    @Override
    public List<InventarioProducto> obtenerTodos() {
        return inventarioProductoDao.findAll();
    }

    @Override
    public InventarioProducto guardar(InventarioProducto inventarioProducto) {
        return inventarioProductoDao.save(inventarioProducto);
    }

    public Page<InventarioProducto> obtenerTodosPaginado(Pageable pageable) {
        return inventarioProductoDao.findAll(pageable);
    }


    @Override
    public void actualizarRevisado(int idInventario, int idProducto, boolean revisado) {
        Optional<InventarioProducto> optionalInventarioProducto = inventarioProductoDao.findById(new InventarioProductoId(idInventario, idProducto));
        if (optionalInventarioProducto.isPresent()) {
            revisado  = true;
            InventarioProducto inventarioProducto = optionalInventarioProducto.get();
            inventarioProducto.setRevisado(revisado);
            inventarioProductoDao.save(inventarioProducto);
            System.out.println(inventarioProducto.toString());
        } else {
            throw new IllegalArgumentException("No se encontró el inventario del producto");
        }
    }


    public void eliminar(InventarioProducto inventarioProducto) {
        inventarioProductoDao.delete(inventarioProducto);
    }

    public boolean existeInventarioProducto(int idInventario, int idProducto) {
        InventarioProductoId inventarioProductoId = new InventarioProductoId(idInventario, idProducto);
        return inventarioProductoDao.existsById(inventarioProductoId);
    }

    public InventarioProducto getByIdProductInventory(int idProducto, int idInventory) {
        return inventarioProductoDao.findById(new InventarioProductoId(idInventory,idProducto)).get();
    }



}
