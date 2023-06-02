package com.stockcontroll.model;

import javax.persistence.*;


@Entity
@Table(name = "inventario_producto")
public class InventarioProducto {

    @EmbeddedId
    private InventarioProductoId id;

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    @MapsId(value = "idInventario")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @MapsId(value = "idProducto")
    private Producto producto;

    @Column(name = "revisado")
    private boolean revisado;


    public InventarioProductoId getId() {
        return id;
    }

    public void setId(InventarioProductoId id) {
        this.id = id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isRevisado() {
        return revisado;
    }

    @Override
    public String toString() {
        return "InventarioProducto{" +
                "id=" + id +
                ", inventario=" + inventario.getId() +
                ", producto=" + producto.getId() +
                ", revisado=" + revisado +
                '}';
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }
}
