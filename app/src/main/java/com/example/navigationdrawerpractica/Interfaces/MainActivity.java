package com.example.navigationdrawerpractica.Interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.navigationdrawerpractica.MiCuenta.fragmen_myAccount;
import com.example.navigationdrawerpractica.Food.Food;
import com.example.navigationdrawerpractica.Food.DetallePersonaFragment;
import com.example.navigationdrawerpractica.Food.Fragment_food;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Confirmados.Order2;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes.FragmentPendientes;
import com.example.navigationdrawerpractica.MyOrders.Fragment.Pendientes.Order;
import com.example.navigationdrawerpractica.MyOrders.HomeFragment;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Saldo.fragment_balance;
import com.google.android.material.navigation.NavigationView;

import com.example.navigationdrawerpractica.Repositorio;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //variable del fragment detalle
    DetallePersonaFragment detallePersonaFragment;
    FragmentPendientes fragmentPendientes = new FragmentPendientes();
    HomeFragment homeFragment = new HomeFragment();

    public static Repositorio repo = new Repositorio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        //lo sgt se implementa luego de haber implementado NavigationView.OnNavigationItemSelectedListener
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargar fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new Fragment_food());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //para cerrar automaticamente el menu
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.myOrders){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new HomeFragment());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.food){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_food());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.myAccount){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragmen_myAccount());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.balance){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_balance());
            fragmentTransaction.commit();
        } //logout

        if(menuItem.getItemId() == R.id.logout){
            System.out.println("Salio de la app");
            finish();
            System.exit(0);

        } //logout
        return false;
    }


    @Override
    public void enviarPersona(Food food) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto", food);
        detallePersonaFragment.setArguments(bundleEnvio);


        //CArgar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }
    @Override
    public void enviarOrdenPendiente(Order food) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio

        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("orderP", food);
        fragmentPendientes.setArguments(bundleEnvio);

        Order o1 = new Order("prueba79","$60",R.drawable.food_empanada);
        Order o2 = new Order("prueba80","$80",R.drawable.food_empanada);
//        r.putOrder(o1);
       // r.putOrder(o2);
/*
        //CArgar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragmentPendientes);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
*/
        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }



    @Override
    public void enviarPersona(Order2 food) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto", food);
        detallePersonaFragment.setArguments(bundleEnvio);

        //CArgar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }




}
