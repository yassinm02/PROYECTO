package com.stockcontroll.data_DAO;

import com.stockcontroll.model_POJO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Integer> {
}
