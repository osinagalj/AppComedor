package com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes;

import java.io.Serializable;

import java.io.Serializable;

public class Order implements Serializable {
    private String nombre;
    private String fechanacimiento;
    private int imagenid;

    public Order(){}

    public Order(String nombre, String fechanacimiento, int imagenid) {
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.imagenid = imagenid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
