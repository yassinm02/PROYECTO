package com.stockcontroll.model_POJO;

import javax.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "web")
    private String website;

    @Column(name = "observaciones")
    private String observations;



}
