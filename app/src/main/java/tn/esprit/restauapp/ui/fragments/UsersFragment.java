package tn.esprit.restauapp.ui.fragments;

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
import tn.esprit.restauapp.adapter.UsersAdapter;
import tn.esprit.restauapp.database.AppDataBase;
import tn.esprit.restauapp.entity.User;


public class UsersFragment extends Fragment {


    private AppDataBase database ;

    private List<User> userList = new ArrayList<User>();

    private RecyclerView mRecyclerView;
    private UsersAdapter usersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users,container,false);

        database = AppDataBase.getAppDatabase(getActivity().getApplicationContext());

        mRecyclerView = rootView.findViewById(R.id.recyelviewusers);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        userList = database.userDao().getAll();

        usersAdapter = new UsersAdapter(getActivity(), userList);

        mRecyclerView.setAdapter(usersAdapter);


        return rootView;
    }
}
