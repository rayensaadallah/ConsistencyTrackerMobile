package tn.esprit.restauapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import tn.esprit.restauapp.R;

public class MenuActivity extends AppCompatActivity {
    ImageView imgMenu;
    String nameResto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imgMenu= findViewById(R.id.imagemenu);
        System.out.println(getIntent().getStringExtra("nameResto").toString());
        String nameResto=getIntent().getStringExtra("nameResto").toString();
        imgMenu.setImageResource(getResources().getIdentifier(nameResto,"drawable", getPackageName()));
    }
}