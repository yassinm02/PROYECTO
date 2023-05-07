package com.stockcontroll.cotroller;


import com.stockcontroll.model.Proveedor;
import com.stockcontroll.service.Proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getProveedorList(){
        return proveedorService.findAll();
    }

    @PostMapping
    public void postProveedor(@RequestBody Proveedor proveedor){
        this.proveedorService.save(proveedor);
    }
}
