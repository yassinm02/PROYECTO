package com.stockcontroll.cotroller;


import com.stockcontroll.model.TipoIva;
import com.stockcontroll.service.TipoIva.TipoIvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipoiva")
public class TipoIvaController {

    @Autowired
    private TipoIvaService tipoIvaService;

    @GetMapping
    public List<TipoIva> getProveedorList(){
        return tipoIvaService.findAll();
    }

    @PostMapping
    public void postProveedor(@RequestBody TipoIva tipoIva){
        this.tipoIvaService.save(tipoIva);
    }


}
