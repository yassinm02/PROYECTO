package com.stockcontroll.cotroller;


import com.stockcontroll.model.Proveedor;
import com.stockcontroll.service.Proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping()
    public List<Proveedor> listProveedores() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProveedorById(@PathVariable int id) {

        if (proveedorService.ProveedorExists(id)) {
            return new ResponseEntity<>("NO HAY PROVEEDOR CON ID: " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(proveedorService.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProveedor(@PathVariable int id) {
        if (!proveedorService.ProveedorExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un proveedor con el ID: " + id);
        }

        proveedorService.deleteById(id);

        return ResponseEntity.ok("Proveedor borrado exitosamente");
    }


    @PostMapping
    public ResponseEntity<?> saveProveedor(@RequestBody Proveedor proveedor) {
        proveedorService.save(proveedor);
        return new ResponseEntity<>("PROVEEDOR GUARDADO", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProveedor(@RequestBody Proveedor proveedor, @PathVariable int id) {
        if (proveedorService.ProveedorExists(id)) {
            return new ResponseEntity<>("NO HAY PROVEEDOR CON ID: " + id, HttpStatus.NOT_FOUND);
        }
        proveedor.setId(id);

        try {
            proveedorService.save(proveedor);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR AL EDITAR PROVEEDOR", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("PROVEEDOR EDITADO", HttpStatus.CREATED);
    }

}

