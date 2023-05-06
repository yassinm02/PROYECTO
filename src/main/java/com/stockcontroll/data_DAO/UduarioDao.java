package com.stockcontroll.data_DAO;

import com.stockcontroll.model_POJO.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UduarioDao extends JpaRepository<Usuario, Integer> {
}
