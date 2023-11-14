package tn.esprit.restauapp.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.Restaurant;
import tn.esprit.restauapp.entity.User;


public class AddFragment extends Fragment {

    EditText nameResto, lieuResto;
    Button btnadd, btnResto;
    ImageView imgResto;
    private AppDataBase database;
    Restaurant restaurant;

    String SelectedImg;
    public static final int STORAGE_PERMISSION = 1;
    public static final int SELECT_IMG1 = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_admin, container, false);
        database = AppDataBase.getAppDatabase(getContext());


        nameResto = root.findViewById(R.id.nameResto);
        lieuResto = root.findViewById(R.id.lieu);
        imgResto = root.findViewById(R.id.imgResto);

        btnResto = root.findViewById(R.id.btnResto);
        btnadd = root.findViewById(R.id.btnadd);
        SelectedImg = "";
        //Add image resto
        btnResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectYourImageResto();


            }

        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveRestaurant();
            }
        });

        return root;
    }

    private void selectYourImageResto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, SELECT_IMG1);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectYourImageResto();

            } else {
                Toast.makeText(getContext(), "Permission Denied:", Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMG1 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri selecImageUri = data.getData();
                if (selecImageUri != null) {
                    try {

                        InputStream inputStream = getContext().getContentResolver().openInputStream(selecImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgResto.setImageBitmap(bitmap);
                        imgResto.setVisibility(getView().VISIBLE);
                        SelectedImg = getPathFromUri(selecImageUri);


                    } catch (Exception exception) {
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }


            }

        }
    }

    private void saveRestaurant() {

        if(validator()) {
            final Restaurant restaurant = new Restaurant();
            restaurant.setName(nameResto.getText().toString());
            restaurant.setLieu(lieuResto.getText().toString());
            restaurant.setImagePathResto(SelectedImg);

            database.restaurantDao().insertOne(restaurant);

            Toast.makeText(getContext(), "Restaurant Aded  !", Toast.LENGTH_SHORT).show();

        }
    }


    private String getPathFromUri(Uri uri) {
        String filePath;
        Cursor cursor = getContext().getContentResolver()
                .query(uri, null, null, null, null);
        if (cursor == null) {
            filePath = uri.getPath();

        } else {

            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;

    }

    //validation
    public boolean validator() {


        if (nameResto.getText().toString().length() == 0
                || lieuResto.getText().toString().length() == 0) {
            Toast.makeText(getContext(), "Name and Place must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }
        restaurant = database.restaurantDao().findByName(nameResto.getText().toString());

        if (restaurant != null) {
            Toast.makeText(getContext(), "Name of restaurant  exist in database !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
