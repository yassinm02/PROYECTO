package com.stockcontroll.service.TipoIva;

import com.stockcontroll.data_DAO.TipoIvaDao;
import com.stockcontroll.model_POJO.TipoIva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoIvaServiceImpl implements TipoIvaService{

    @Autowired
    private TipoIvaDao tipoIvaDao;

    @Override
    public List<TipoIva> findAll() {
        return tipoIvaDao.findAll();
    }

    @Override
    public Optional<TipoIva> findById(int id) {
        return tipoIvaDao.findById(id);
    }

    @Override
    public TipoIva save(TipoIva tipoIva) {
        return tipoIvaDao.save(tipoIva);
    }

    @Override
    public void deleteById(int id) {
        tipoIvaDao.deleteById(id);
    }
}
