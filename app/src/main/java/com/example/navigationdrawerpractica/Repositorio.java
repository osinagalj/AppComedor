package com.example.navigationdrawerpractica;

import com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes.Order;

import java.util.ArrayList;

public class Repositorio {

    private int id;
    ArrayList<Order> ordenes = new ArrayList<Order>();

    public Repositorio(){

    }

    public void putOrder(Order o){
        ordenes.add(o);
    }
    public  ArrayList<Order> getOrders(){
        return ordenes;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}
