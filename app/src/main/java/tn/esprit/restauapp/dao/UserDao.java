package tn.esprit.restauapp.dao;



import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import tn.esprit.restauapp.entity.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Query("SELECT * FROM user_table WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_table WHERE email LIKE :email AND password LIKE :password LIMIT 1")
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM user_table WHERE email == :email LIMIT 1")
    User findByEmail(String email);

    @Query("SELECT * FROM user_table WHERE role == :role LIMIT 1")
    User findByRole(String role);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertOne(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

}

