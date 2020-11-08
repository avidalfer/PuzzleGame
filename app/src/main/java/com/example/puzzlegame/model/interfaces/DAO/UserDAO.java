package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlegame.model.User;

import java.util.List;

@Dao
public abstract class UserDAO {
    @Query("SELECT * FROM users")
    public abstract List<User> getAll();

    @Query("SELECT * FROM users WHERE rowid IN (:userIds)")
    public abstract List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE name LIKE :name")
    public abstract User findByName(String name);

    @Insert
    public abstract void insertUser(User... Users);

    @Delete
    public abstract void delete(User User);

    @Update
    public abstract void updateUser(User user);
}
