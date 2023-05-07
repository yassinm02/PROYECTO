package com.stockcontroll.model_POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stockcontroll.model_POJO.enums.EstadoProducto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "marca")
    private String marca;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProducto estado;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_iva")
    private TipoIva tipoIva;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor")
    private Proveedor proveedor;

    @Column(name = "imagen")
    private byte[] image;

    @Column(name = "cod_barras")
    private String codBarras;

    public Producto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public TipoIva getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(TipoIva tipoIva) {
        this.tipoIva = tipoIva;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }
}
