package com.example.navigationdrawerpractica.Interfaces;

import com.example.navigationdrawerpractica.Food.Food;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.Order2;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes.Order;

public interface iComunicaFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    public void enviarPersona(Food food); //se transportara un objeto de tipo persona

    void enviarOrdenPendiente(Order order);
    void enviarPersona(Order2 order);


    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)
}
