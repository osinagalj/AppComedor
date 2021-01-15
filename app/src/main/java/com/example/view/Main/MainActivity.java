package com.example.view.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.view.Fila.Fragment_fila;
import com.example.view.Food.NestedRecyclerFood.FragmentFood;
import com.example.view.MyAccount.fragmen_myAccount;
import com.example.view.MyOrders.HomeFragment;
import com.example.view.R;
import com.example.view.Saldo.fragment_balance;
import com.google.android.material.navigation.NavigationView;

import Model.CommonUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //variable del fragment detalle

    CommonUser loggedUser ;
    //TODO get the user using DAO, find user by id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

        //TODO
        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        int id_user  =(Integer) b.get("ID_LOGGER_USER"); //con este id habria que usar el DAO y obtener el usuario by id
        //TODO loggedUser = DAO.getUser(id_user)

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargar fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new FragmentFood());
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
            fragmentTransaction.replace(R.id.container_fragment,new FragmentFood());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.myAccount){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragmen_myAccount());
            fragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.Fila){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Fragment_fila());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.balance){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_balance());
            fragmentTransaction.commit();
        } //logout

        if(menuItem.getItemId() == R.id.logout){
            finish();

        } //logout
        return false;
    }

    public void setLoggedUser(CommonUser logged){
        this.loggedUser = logged;
    }


}
