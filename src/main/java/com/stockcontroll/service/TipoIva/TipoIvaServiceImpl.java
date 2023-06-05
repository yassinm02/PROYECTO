package com.stockcontroll.service.TipoIva;

import com.stockcontroll.repository.TipoIvaRepository;
import com.stockcontroll.model.TipoIva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoIvaServiceImpl implements TipoIvaService{

    @Autowired
    private TipoIvaRepository tipoIvaRepository;

    @Override
    public List<TipoIva> findAll() {
        return tipoIvaRepository.findAll();
    }

    @Override
    public Optional<TipoIva> findById(int id) {
        return tipoIvaRepository.findById(id);
    }

    @Override
    public TipoIva save(TipoIva tipoIva) {
        return tipoIvaRepository.save(tipoIva);
    }

    @Override
    public void deleteById(int id) {
        tipoIvaRepository.deleteById(id);
    }

    @Override
    public boolean IvaExists(int id) {
        return tipoIvaRepository.existsById(id);
    }
}
