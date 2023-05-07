package com.stockcontroll.data;

import com.stockcontroll.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UduarioDao extends JpaRepository<Usuario, Integer> {
}
