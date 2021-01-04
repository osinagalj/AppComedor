package com.example.navigationdrawerpractica.MiCuenta;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class fragmen_myAccount extends Fragment {

    Adapter_user adapter_data;
    RecyclerView recyclerViewData;
    ArrayList<User_data> listaData;

    //EditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
       // txtnombre = view.findViewById(R.id.txtnombre);

        recyclerViewData = view.findViewById(R.id.recyclerView2);
        listaData = new ArrayList<User_data>();
        cargarLista();
        mostrarData();

        return view;
    }
    public void cargarLista(){

        listaData.add(new User_data("Nombre","Lautaro"));
        listaData.add(new User_data("Apellido","Osinaga"));
        listaData.add(new User_data("DNI","41854419"));
        listaData.add(new User_data("Categoria","Alumno"));
        listaData.add(new User_data("Condicion","Vegetariano"));
        listaData.add(new User_data("Contraseña","*********"));


    }
    private void mostrarData(){
        recyclerViewData.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_data = new Adapter_user(getContext(), listaData);
        recyclerViewData.setAdapter(adapter_data);

    }

    @Override
    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Mi Cuenta");
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}