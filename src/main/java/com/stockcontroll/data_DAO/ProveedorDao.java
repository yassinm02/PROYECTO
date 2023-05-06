package com.stockcontroll.data_DAO;

import com.stockcontroll.model_POJO.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorDao extends JpaRepository<Proveedor, Integer> {
}
