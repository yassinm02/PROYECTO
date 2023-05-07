package com.stockcontroll.cotroller;

import com.stockcontroll.model.Producto;
import com.stockcontroll.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Producto> ListProducts(){
        return productService.findAll();
    }

    @GetMapping("/buscar")
    public List<Producto> buscarProductos(@RequestParam(name = "name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){

        if(!productService.ProductExists(id)) {
            return new ResponseEntity<>("NO HAY PRODUCTO CON ID: "+id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productService.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if (!productService.ProductExists(id)){
            return new ResponseEntity<>("NO HAY PRODUCTO CON ID: "+id, HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);

        return new ResponseEntity<>("PRODUCTO BORRADO CON EXITO", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Producto producto){
        productService.save(producto);
        return new ResponseEntity<>("PRODUCTO GUARDADO", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editProducto(@RequestBody Producto producto){
        if (!productService.ProductExists(producto.getId())){
            return new ResponseEntity<>("NO HAY PRODUCTO CON ID: "+producto.getId(), HttpStatus.NOT_FOUND);
        }
        try {
            productService.save(producto);
        }
        catch (Exception e){
            return new ResponseEntity<>("ERROR AL EDITAR PRODUCTO", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("PRODUCTO EDITADO", HttpStatus.CREATED);
    }

}
