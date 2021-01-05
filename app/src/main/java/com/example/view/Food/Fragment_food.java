package com.example.view.Food;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Interfaces.iComunicaFragments;
import com.example.view.R;

import java.util.ArrayList;

public class Fragment_food extends Fragment {


    AdapterPersonas adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Food> listaFoods;



    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView);
        listaFoods = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaFoods.add(new Food("food_alfajor_pepitos","$50",R.drawable.food_alfajor_pepitos));
        listaFoods.add(new Food("food_botella_coca","$50",R.drawable.food_botella_coca));
        listaFoods.add(new Food("food_empanada","$50",R.drawable.food_empanada));
        listaFoods.add(new Food("food_milanesas_con_fritas","$50",R.drawable.food_milanesas_con_fritas));
        listaFoods.add(new Food("food_pepas_trio","$50",R.drawable.food_pepas_trio));
        listaFoods.add(new Food("food_vaso_coca","$50",R.drawable.food_vaso_coca));
        listaFoods.add(new Food("food_turron_arcor","$50",R.drawable.food_turron_arcor));
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterPersonas(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPersonas);

        adapterPersonas.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nombre = listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre();

               Toast.makeText(getContext(), "Seleccionó: "+ listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarPersona(listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle("Comidas");
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
