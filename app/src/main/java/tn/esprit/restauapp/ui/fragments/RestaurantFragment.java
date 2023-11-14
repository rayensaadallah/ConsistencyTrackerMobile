package tn.esprit.restauapp.ui.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.restauapp.R;
import tn.esprit.restauapp.adapter.RestaurantAdapter;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.Restaurant;


public class RestaurantFragment extends Fragment {

    private AppDataBase database ;

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    private RecyclerView mRecyclerView;
    private RestaurantAdapter restaurantAdapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_restaurant_admin, container, false);
        database = AppDataBase.getAppDatabase(getActivity().getApplicationContext());

        mRecyclerView = root.findViewById(R.id.recyleviewrestaurant);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        restaurants = database.restaurantDao().getAll();


        restaurantAdapter = new RestaurantAdapter(getActivity(), restaurants);

        mRecyclerView.setAdapter(restaurantAdapter);
        return root;
    }
}