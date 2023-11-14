package tn.esprit.restauapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.User;

public class RegistrationActivity extends AppCompatActivity {
    private AppDataBase database ;
    private EditText edSignUpName, edSignUpEmail, edSignUpPassword;
    private static final String EMAIL_PATTERN= "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        database = AppDataBase.getAppDatabase(this);
        edSignUpName = findViewById(R.id.firstname);
        edSignUpEmail = findViewById(R.id.email);
        edSignUpPassword = findViewById(R.id.password);
    }
    public void signin(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }


    //register

    public void doSignUp(View view) {

        if(validator()) {
            //TODO CHECK EMAIL AND ADD USER
            User tmpUser = new User(edSignUpName.getText().toString(),
                    edSignUpEmail.getText().toString(), edSignUpPassword.getText().toString(),"User");
            System.out.println(tmpUser);
            database.userDao().insertOne(tmpUser);
            Toast.makeText(this, "User Aded  !", Toast.LENGTH_SHORT).show();

            finish();
        }
    }


    public boolean validator(){

        if (edSignUpName.getText().toString().length() == 0

                || edSignUpEmail.getText().toString().length() == 0
                || edSignUpPassword.getText().toString().length() == 0
        ){
            Toast.makeText(this, "Data must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!edSignUpEmail.getText().toString().matches(EMAIL_PATTERN)){
            Toast.makeText(this, "Email is not valid !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(edSignUpPassword.getText().length() >8)){
            Toast.makeText(this, "Password must be greater than 8!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //TODO 2 Check user existance in database

        User tmpUser = AppDataBase.getAppDatabase(getApplicationContext()).userDao().findByEmail(edSignUpEmail.getText().toString());
        if (tmpUser != null){
            Toast.makeText(this, "User exist in database !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

