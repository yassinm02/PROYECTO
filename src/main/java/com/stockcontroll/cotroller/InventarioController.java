package com.stockcontroll.cotroller;

import com.stockcontroll.model.Inventario;
import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.model.InventarioProductoId;
import com.stockcontroll.model.Producto;
import com.stockcontroll.service.Inventario.InventarioService;
import com.stockcontroll.service.InventarioProducto.InventarioProductoService;
import com.stockcontroll.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodos() {
        List<Inventario> inventarios = inventarioService.obtenerTodos();
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable int id) {
        Inventario inventario = inventarioService.obtenerPorId(id).orElse(null);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.guardar(inventario);
        List<Producto> productos = productService.findAll();
        for (Producto producto : productos) {
            InventarioProducto inventarioProducto = new InventarioProducto();
            InventarioProductoId inventarioProductoId = new InventarioProductoId(nuevoInventario.getId(), producto.getId());
            inventarioProducto.setId(inventarioProductoId);
            inventarioProducto.setInventario(nuevoInventario);
            inventarioProducto.setProducto(producto);
            inventarioProducto.setRevisado(false);
            inventarioProductoService.guardar(inventarioProducto);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
