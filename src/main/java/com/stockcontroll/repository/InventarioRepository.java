package com.stockcontroll.repository;

import com.stockcontroll.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {


}
