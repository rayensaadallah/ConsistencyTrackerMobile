package tn.esprit.restauapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.Restaurant;

public class UpdateRestaurantActivity extends AppCompatActivity {
    EditText nameResto,lieuResto;

    ImageView imgResto ;
    Button btnupdate,btnResto;
    public static final int STORAGE_PERMISSION = 1;
    public static final int SELECT_IMG1 = 1;

    private AppDataBase database ;

    String SelectedImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurant);
        database = AppDataBase.getAppDatabase(UpdateRestaurantActivity.this);
        nameResto = findViewById(R.id.nameResto);
        lieuResto = findViewById(R.id.lieu);
        imgResto = findViewById(R.id.imgResto);
        btnupdate = findViewById(R.id.btnupdate);
        btnResto = findViewById(R.id.btnResto);
        nameResto.setText(getIntent().getStringExtra("name").toString());
        lieuResto.setText(getIntent().getStringExtra("lieu").toString());
        btnResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectYourImageResto();



            }

        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRestaurant();
            }
        });


    }

    private void saveRestaurant(){
        if (nameResto.getText().toString().length() == 0
                || lieuResto.getText().toString().length() == 0 ){
            Toast.makeText(UpdateRestaurantActivity.this, "Name and Place must not be empty !", Toast.LENGTH_SHORT).show();
            return ;
        }
        int id=getIntent().getIntExtra("id",0);
        System.out.println(id+"ok");
        Restaurant restaurant=  database.restaurantDao().findById(id);
        restaurant.setUid(getIntent().getIntExtra("id",0));
        restaurant.setName(nameResto.getText().toString());
        restaurant.setLieu(lieuResto.getText().toString());

        restaurant.setImagePathResto(SelectedImg);
        System.out.println(restaurant);



        database.restaurantDao().update(restaurant);
        startActivity(new Intent(UpdateRestaurantActivity.this,WelcomeActivity.class));


        Toast.makeText(UpdateRestaurantActivity.this ,"Restaurant Updated  !", Toast.LENGTH_SHORT).show();






    }
    private void selectYourImageResto() {
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,SELECT_IMG1);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==STORAGE_PERMISSION && grantResults.length>0){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                selectYourImageResto();

            }
            else {
                Toast.makeText(UpdateRestaurantActivity.this,"Permission Denied:",Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMG1 && resultCode == Activity.RESULT_OK){
            if(data!= null){
                Uri selecImageUri = data.getData();
                if (selecImageUri !=null ){
                    try{

                        InputStream inputStream = getContentResolver().openInputStream(selecImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgResto.setImageBitmap(bitmap);
                        imgResto.setVisibility(View.VISIBLE);
                        SelectedImg = getPathFromUri(selecImageUri);



                    }catch(Exception exception){
                        Toast.makeText(UpdateRestaurantActivity.this,exception.getMessage(),Toast.LENGTH_SHORT).show();




                    }
                }


            }

        }
    }
    private String getPathFromUri(Uri uri)
    {
        String filePath;
        Cursor cursor = getContentResolver()
                .query(uri,null,null,null,null);
        if (cursor == null )
        {
            filePath =uri.getPath();

        }else {

            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath=cursor.getString(index);
            cursor.close();
        }
        return  filePath;

    }

}

