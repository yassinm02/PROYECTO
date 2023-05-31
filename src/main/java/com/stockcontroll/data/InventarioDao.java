package com.stockcontroll.data;

import com.stockcontroll.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioDao extends JpaRepository<Inventario, Integer> {


}
