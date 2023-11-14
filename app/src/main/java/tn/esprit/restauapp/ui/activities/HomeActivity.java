package tn.esprit.restauapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.restauapp.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void register(View view) {
        startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));

    }
    public void login(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));

    }
}
