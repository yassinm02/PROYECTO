package com.stockcontroll.cotroller;


import com.itextpdf.text.DocumentException;
import com.stockcontroll.model.Proveedor;
import com.stockcontroll.service.Proveedor.ProveedorService;
import com.stockcontroll.util.ProductPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=proveedores" + LocalDate.now() + ".pdf";
        response.setHeader(headerkey, headervalue);
        List <Proveedor> proveedorList = proveedorService.findAll();
        ProductPdfGenerator.generatePdfProveedores(proveedorList, response);
    }

}

