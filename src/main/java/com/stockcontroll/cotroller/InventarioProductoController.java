package com.stockcontroll.cotroller;

import com.itextpdf.text.DocumentException;
import com.stockcontroll.model.InventarioProducto;
import com.stockcontroll.service.InventarioProducto.InventarioProductoService;
import com.stockcontroll.util.PdfGenerator;
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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/inventario-producto")
public class InventarioProductoController {

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @GetMapping
    public List<InventarioProducto> obtenerTodos() {
        return inventarioProductoService.obtenerTodos();
    }

    @GetMapping("/lista/{idInventario}")
    public ResponseEntity<List<InventarioProducto>> obtenerPorIdInventario(@PathVariable int idInventario, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<InventarioProducto> inventarioProductos = inventarioProductoService.obtenerPorIdInventario(idInventario);
        if (inventarioProductos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        int start = page * size;
        int end = Math.min(start + size, inventarioProductos.size());
        List<InventarioProducto> paginatedProductos = inventarioProductos.subList(start, end);

        return ResponseEntity.ok(paginatedProductos);
    }


    @GetMapping("/lista")
    public ResponseEntity<Page<InventarioProducto>> obtenerTodos(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<InventarioProducto> productosPage = inventarioProductoService.obtenerTodosPaginado(pageable);
        return ResponseEntity.ok(productosPage);
    }


    @GetMapping("/{idInventario}/{idProducto}")
    public ResponseEntity<InventarioProducto> obtenerPorId(@PathVariable int idInventario, @PathVariable int idProducto) {
        Optional<InventarioProducto> inventarioProducto = inventarioProductoService.obtenerPorIdInventarioYProducto(idInventario, idProducto);
        return inventarioProducto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventarioProducto> guardar(@RequestBody InventarioProducto inventarioProducto) {
        InventarioProducto nuevoInventarioProducto = inventarioProductoService.guardar(inventarioProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventarioProducto);
    }

    @PutMapping("/actualizar-revisado/{idInventario}/{idProducto}/{revisado}")
    public void actualizarRevisado(@PathVariable int idInventario, @PathVariable int idProducto, @PathVariable boolean revisado) {
        inventarioProductoService.actualizarRevisado(idInventario, idProducto, revisado);
    }

    @DeleteMapping("/{idInventario}/{idProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable int idInventario, @PathVariable int idProducto) {
        Optional<InventarioProducto> inventarioProducto = inventarioProductoService.obtenerPorIdInventarioYProducto(idInventario, idProducto);
        if (inventarioProducto.isPresent()) {
            inventarioProductoService.eliminar(inventarioProducto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadPdf(@PathVariable int id, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        String filename = "productos_" + LocalDate.now() + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        List<InventarioProducto> productoList = inventarioProductoService.obtenerPorIdInventario(id);

        PdfGenerator.generatePdfInventarioProductos(productoList, response);
    }



}