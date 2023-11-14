package tn.esprit.restauapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.adapter.ListRestaurantAdapter;
import tn.esprit.restauapp.adapter.RestaurantAdapter;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.Restaurant;

public class HomeUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Restaurant> restaurants;
    private ListRestaurantAdapter mAdapter;
    Button btnlogout;
    private AppDataBase database ;
    private AppBarConfiguration mAppBarConfiguration;

    public static final String EMAIL_KEY = "EMAIL";

    public static final String PWD_KEY = "PWD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        database = AppDataBase.getAppDatabase(this);

        recyclerView = findViewById(R.id.recyler_restaurant);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        restaurants = database.restaurantDao().getAll();
        TextView navUsername = (TextView) findViewById(R.id.nameUser);


        navUsername.setText("My Name is  :"+ getIntent().getStringExtra("name").toString());
        ;




        mAdapter = new ListRestaurantAdapter(this, restaurants);

        recyclerView.setAdapter(mAdapter);
        btnlogout=findViewById(R.id.btnlogoutuser);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences SM = getSharedPreferences("tn.esprit.restaurant", 0);
                SharedPreferences.Editor edit = SM.edit();
                edit.putString(EMAIL_KEY," ");
                edit.putString(PWD_KEY," ");
                edit.commit();


                Intent intent = new Intent(HomeUserActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });
    }
}