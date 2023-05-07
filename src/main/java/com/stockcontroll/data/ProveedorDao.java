package com.stockcontroll.data;

import com.stockcontroll.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorDao extends JpaRepository<Proveedor, Integer> {
}
