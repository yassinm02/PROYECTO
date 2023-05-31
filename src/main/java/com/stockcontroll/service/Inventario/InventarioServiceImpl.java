package com.stockcontroll.service.Inventario;

import com.stockcontroll.data.InventarioDao;
import com.stockcontroll.model.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioDao inventarioRepository;

    @Override
    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    @Override
    public Optional<Inventario> obtenerPorId(int id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public void eliminar(int id) {
        inventarioRepository.deleteById(id);
    }
}
