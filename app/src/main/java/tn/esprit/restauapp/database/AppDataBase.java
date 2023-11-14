package tn.esprit.restauapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.restauapp.dao.RestaurantDao;
import tn.esprit.restauapp.dao.UserDao;
import tn.esprit.restauapp.entity.Restaurant;
import tn.esprit.restauapp.entity.User;

@Database(entities = {
        User.class,
        Restaurant.class
}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    private static AppDataBase instance;

    public abstract RestaurantDao restaurantDao();
    public abstract UserDao userDao();
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "restau_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}