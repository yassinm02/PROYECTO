package com.stockcontroll.cotroller;


import com.stockcontroll.model.TipoIva;
import com.stockcontroll.service.TipoIva.TipoIvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipoiva")
public class TipoIvaController {

    @Autowired
    private TipoIvaService tipoIvaService;

    @GetMapping
    public List<TipoIva> getIvaList() {
        return tipoIvaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findIvaById(@PathVariable int id) {

        if (!tipoIvaService.IvaExists(id)) {
            return new ResponseEntity<>("NO HAY Tipo de IVA CON ID: " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tipoIvaService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public void postIva(@RequestBody TipoIva tipoIva) {
        this.tipoIvaService.save(tipoIva);
    }

}
