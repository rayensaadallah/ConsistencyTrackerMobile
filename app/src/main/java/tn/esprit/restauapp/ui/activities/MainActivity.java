package tn.esprit.restauapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import tn.esprit.restauapp.R;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    ImageView imgperson;
    TextView nomlogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgperson = findViewById(R.id.imglogo);
        nomlogo = findViewById(R.id.nomlogo);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // initiliaze animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        //start top animation
        nomlogo.setAnimation(animation);
        //initialize object animation
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                nomlogo,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)

        );
        //set duration
        objectAnimator.setDuration(500);
        //set repeat count
        objectAnimator.setRepeatCount((ValueAnimator.INFINITE));
        objectAnimator.setRepeatCount((ValueAnimator.REVERSE));
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.animationimage);
        imgperson.setAnimation(animation2);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        },3000);
    }
//        objectAnimator.start();



}

