package com.example.navigationdrawerpractica.MiCuenta;

import java.io.Serializable;

public class User_data implements Serializable {
    private String campo;
    private String descripcion;

    public User_data(){}

    public User_data(String campo,String descripcion){
        this.campo = campo;
        this.descripcion = descripcion;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        campo = campo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion;
    }



}
