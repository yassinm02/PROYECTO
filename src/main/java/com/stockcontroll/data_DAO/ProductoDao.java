package com.stockcontroll.data_DAO;

import com.stockcontroll.model_POJO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductoDao extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p where p.name like %:productName%")
    List<Producto> findByProductNameContaining(@Param("productName") String productName);
}
