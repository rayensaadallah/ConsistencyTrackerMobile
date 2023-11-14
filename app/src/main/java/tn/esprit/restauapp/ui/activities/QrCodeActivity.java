package tn.esprit.restauapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.restauapp.R;

public class QrCodeActivity extends AppCompatActivity {
    Button scanbtn;
    public static TextView scantext;
    String nameResto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);


//        scantext=(TextView)findViewById(R.id.scantext);
        scanbtn=(Button) findViewById(R.id.btnscanne);
        scantext =findViewById(R.id.textscan);
        System.out.println(scantext.getText());
        nameResto= getIntent().getStringExtra("name").toString();
        System.out.println(nameResto+"gggggg");




        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrCodeActivity.this, ScannerViewActivity.class);
                intent.putExtra("nameResto",nameResto);
                startActivity(intent);
            }
        });
    }



}