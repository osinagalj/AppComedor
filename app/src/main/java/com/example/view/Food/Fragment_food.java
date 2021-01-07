package com.example.view.Food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Food.Nested2.Nested2Main;
import com.example.view.Interfaces.iComunicaFragments;
import com.example.view.R;

import java.util.ArrayList;

import model.Restaurant;

public class Fragment_food extends Fragment {


    AdapterPersonas adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<model.Food> listaFoods;
    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;
    RecyclerView mainRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_food,container,false);
        recyclerViewPersonas = view.findViewById(R.id.recyclerView);
        listaFoods = new ArrayList<>();



        cargarLista();
       mostrarData();

      /*

        //setContentView(R.layout.activity_main);
        RecyclerView rvItem = view.findViewById(R.id.rv_item);
       LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());  //MainActivity.this

        ItemAdapter itemAdapter = new ItemAdapter(buildItemList());
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
        System.out.println("Entro 5");
*/
        //initData();
        //recyclerViewPersonas = view.findViewById(R.id.mainRecyclerView);
       // MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getContext(),sectionList);
        //recyclerViewPersonas.setAdapter(mainRecyclerAdapter);

       // recyclerViewPersonas.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        return view;
    }
    public void cargarLista(){

        //Restaurant restaurant = new Restaurant(1,"Comedor Unicen","Paraje Arroyo Seco",900,2000);
        listaFoods = Restaurant.restaurant.getProducts();
        /*
        Vector<String> ingredients = new Vector<String>();
        model.Food food1 = new model.Food(0001,"Alfajor Pepitos",6, 20.2f, ingredients,R.drawable.food_alfajor_pepitos);
        listaFoods.add(food1);
        */

/*
        listaFoods.add(new Food("food_alfajor_pepitos","$50",R.drawable.food_alfajor_pepitos));
        listaFoods.add(new Food("food_botella_coca","$50",R.drawable.food_botella_coca));
        listaFoods.add(new Food("food_empanada","$50",R.drawable.food_empanada));
        listaFoods.add(new Food("food_milanesas_con_fritas","$50",R.drawable.food_milanesas_con_fritas));
        listaFoods.add(new Food("food_pepas_trio","$50",R.drawable.food_pepas_trio));
        listaFoods.add(new Food("food_vaso_coca","$50",R.drawable.food_vaso_coca));
        listaFoods.add(new Food("food_turron_arcor","$50",R.drawable.food_turron_arcor));
   */
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterPersonas(getContext(), listaFoods);
        recyclerViewPersonas.setAdapter(adapterPersonas);

        adapterPersonas.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nombre = listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)).getName();

                //Toast.makeText(getContext(), "Seleccionó: "+ listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado

                System.out.println("Ola");
                Intent intent = new Intent(getActivity(), Nested2Main.class);
                //intent.putExtra("food_picked", listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                startActivity(intent);

                //interfaceComunicaFragments.enviarPersona(listaFoods.get(recyclerViewPersonas.getChildAdapterPosition(view)));
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
/*
    private void initData() {

        String sectionOneName = "Action";
        List<Food> sectionOneItems = new ArrayList<>();
        sectionOneItems.add(new Food("food_alfajor_pepitos","$50",R.drawable.food_alfajor_pepitos));
        sectionOneItems.add(new Food("food_botella_coca","$50",R.drawable.food_botella_coca));

        String sectionTwoName = "Adventure";
        List<Food> sectionTwoItems = new ArrayList<>();
        sectionTwoItems.add(new Food("food_alfajor_pepitos","$50",R.drawable.food_alfajor_pepitos));
        sectionTwoItems.add(new Food("food_botella_coca","$50",R.drawable.food_botella_coca));



        sectionList.add(new SectionFood(sectionOneName, sectionOneItems));
        sectionList.add(new SectionFood(sectionTwoName, sectionTwoItems));

    }
*/




}
