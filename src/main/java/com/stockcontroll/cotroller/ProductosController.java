package com.stockcontroll.cotroller;

import com.stockcontroll.model_POJO.Producto;
import com.stockcontroll.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Producto findById(@PathVariable int id){
        return productService.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        productService.deleteById(id);
    }

    @PostMapping
    public void saveProducto(@RequestBody Producto producto){
        productService.save(producto);
    }

    @PutMapping
    public void editProducto(@RequestBody Producto producto){
        productService.save(producto);
    }


}
