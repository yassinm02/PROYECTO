package com.stockcontroll.cotroller;

import com.itextpdf.text.DocumentException;
import com.stockcontroll.model.Producto;
import com.stockcontroll.service.Product.ProductService;
import com.stockcontroll.service.Proveedor.ProveedorService;
import com.stockcontroll.util.ProductPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCheck(){
        try{
            long count = productService.count();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping()
    public List<Producto> ListProducts(){
        return productService.findAll();
    }

    @GetMapping("/list")
    public Page<Producto> obtenerProductos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "searchTerm", required = false) String searchTerm) {

        Pageable pageable = PageRequest.of(page, size);

        if (searchTerm != null && !searchTerm.isEmpty()) {
            return productService.findByName(searchTerm, pageable);
        } else {
            return productService.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){

        if(!productService.ProductExistsById(id)) {
            return new ResponseEntity<>("NO HAY PRODUCTO CON ID: "+id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productService.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if (!productService.ProductExistsById(id)){
            return new ResponseEntity<>("NO HAY PRODUCTO CON ID: "+id, HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);

        return new ResponseEntity<>("PRODUCTO BORRADO CON EXITO", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveProduct(@RequestBody Producto producto) {
        try {
            System.out.println(producto.toString());
            productService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("PRODUCTO GUARDADO");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto: " + e.getMessage());
        }
    }


    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable("id") int id, @RequestBody Producto producto) {
        try {
            if (productService.findById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con ID: " + id);
            }

            producto.setId(id);
            productService.save(producto);

            return ResponseEntity.status(HttpStatus.OK).body("Producto editado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el producto: " + e.getMessage());
        }
    }

    @GetMapping("/barcode/{barcode}")
    private ResponseEntity<?> getByCodBarras(@PathVariable("barcode") String barcode){
        Producto producto = productService.findByCodBarras(barcode);

        return producto == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con codigo de barras: " + barcode) : ResponseEntity.status(HttpStatus.OK).body(producto);

    }


    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=productos" + LocalDate.now() + ".pdf";
        response.setHeader(headerkey, headervalue);
        List <Producto> productoList = productService.findAll();
        ProductPdfGenerator.generatePdfProducts(productoList, response);
    }


}
