package com.example.view.Interfaces;

import com.example.view.Food.Food;
import com.example.view.MyOrders.Fragment.Confirmados.Order2;
import com.example.view.MyOrders.Fragment.Pendientes.Order;

public interface iComunicaFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    public void enviarPersona(Food food); //se transportara un objeto de tipo persona

    void enviarOrdenPendiente(Order order);
   // void enviarPersona(Order2 order);
    void abrirPDF(Order order);

    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)
}
