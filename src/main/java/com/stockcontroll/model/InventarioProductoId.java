package com.stockcontroll.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class InventarioProductoId implements Serializable {

    @Column(name = "id_inventario")
    private int idInventario;

    @Column(name = "id_producto")
    private int idProducto;


    public InventarioProductoId() {}

    public InventarioProductoId(int idInventario, int idProducto) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventarioProductoId)) return false;
        InventarioProductoId that = (InventarioProductoId) o;
        return idInventario == that.idInventario &&
                idProducto == that.idProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInventario, idProducto);
    }
}