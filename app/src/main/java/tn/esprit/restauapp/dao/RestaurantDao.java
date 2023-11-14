package tn.esprit.restauapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauapp.entity.Restaurant;
import tn.esprit.restauapp.entity.User;

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM table_restaurant")
    List<Restaurant> getAll();

    @Query("SELECT * FROM table_restaurant WHERE uid IN (:restaurantIds)")
    List<Restaurant> loadAllByIds(int[] restaurantIds);


    @Query("SELECT * FROM table_restaurant WHERE name == :name LIMIT 1")
    Restaurant findByName(String name);

    @Query("SELECT * FROM table_restaurant WHERE uid == :id LIMIT 1")
    Restaurant findById(int id);

    @Insert
    void insertAll(Restaurant... restaurants);

    @Insert
    void insertOne(Restaurant restaurant);

    @Delete
    void delete(Restaurant restaurant);

    @Update
    void update(Restaurant restaurant);

}
