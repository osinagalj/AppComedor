package com.example.view.MyOrders.Fragment.Pendientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.MyOrders.Fragment.Confirmados.ActivityPdf;
import com.example.view.R;

import java.util.ArrayList;

import DAO.OrderDAO;
import ModeloGian.Order;


public class FragmentPendientes extends Fragment {


    AdapterPendientes adapterPend;
    RecyclerView recyclerViewPersonas;
    ArrayList<Order> listaFoods;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_orders,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView_live);

        listaFoods = new ArrayList<>();
        cargarLista();
        mostrarData();

        return view;
    }

    public void cargarLista(){
       // CommonUser u = new CommonUser("aaa","aaa","Juan", "Perez", LocalDate.of(2000,1,15), 111,new Condition("Celiaco",new HashSet<>()),new Discount(10));
        listaFoods.addAll(OrderDAO.getPendingOrders(null));
    }


    private void mostrarData() {
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPend = new AdapterPendientes(getContext(), listaFoods,this);
        recyclerViewPersonas.setAdapter(adapterPend);

        adapterPend.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActivityPdf.class);
                startActivity(intent);
                //voy a tener que pasarle la orden para el pedido

                //interfaceComunicaFragments.abrirPDF(listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }


}