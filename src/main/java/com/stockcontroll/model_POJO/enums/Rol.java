package com.stockcontroll.model_POJO.enums;

public enum Rol {
    EMPLEADO("empleado"),
    ENCARGADO("encargado"),
    USUARIO("usuario");

    private final String nombre;

    Rol(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
