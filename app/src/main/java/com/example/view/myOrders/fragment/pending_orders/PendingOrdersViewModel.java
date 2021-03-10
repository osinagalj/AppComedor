package com.example.view.myOrders.fragment.pending_orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dao.OrderDAO;
import model.Order;
import model.OrderState;

public class PendingOrdersViewModel extends ViewModel {

    public MutableLiveData<List<Order>> getOrders(){
        return OrderDAO.getOrders(OrderState.PENDING);
    }
}