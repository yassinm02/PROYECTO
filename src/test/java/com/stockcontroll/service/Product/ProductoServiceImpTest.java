package com.stockcontroll.service.Product;

import com.stockcontroll.repository.ProductoRepository;
import com.stockcontroll.model.Producto;
import com.stockcontroll.service.Proveedor.ProveedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class ProductoServiceImpTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProveedorService proveedorService;

    @InjectMocks
    private ProductoServiceImp productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByName_ShouldReturnPageOfProductos() {
        // Arrange
        String name = "example";
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        productos.add(new Producto());
        Page<Producto> expectedPage = new PageImpl<>(productos, pageable, productos.size());

        when(productoRepository.findByNameContaining(name, pageable)).thenReturn(expectedPage);

        // Act
        Page<Producto> result = productoService.findByName(name, pageable);

        // Assert
        assertEquals(expectedPage, result);
        verify(productoRepository, times(1)).findByNameContaining(name, pageable);
    }

    @Test
    void findAll_ShouldReturnListOfProductos() {
        // Arrange
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        productos.add(new Producto());

        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> result = productoService.findAll();

        // Assert
        assertEquals(productos, result);
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void findAllWithPageable_ShouldReturnPageOfProductos() {
        // Arrange
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        productos.add(new Producto());
        Page<Producto> expectedPage = new PageImpl<>(productos, pageable, productos.size());

        when(productoRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Producto> result = productoService.findAll(pageable);

        // Assert
        assertEquals(expectedPage, result);
        verify(productoRepository, times(1)).findAll(pageable);
    }
}