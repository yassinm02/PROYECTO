package com.stockcontroll.service.TipoIva;

import com.stockcontroll.model.TipoIva;

import java.util.List;
import java.util.Optional;

public interface TipoIvaService {

    List<TipoIva> findAll();

    Optional<TipoIva> findById(int id);

    TipoIva save(TipoIva tipoIva);

    void deleteById(int id);

    boolean IvaExists(int id);
}
