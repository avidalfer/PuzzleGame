package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE rowid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE name LIKE :name")
    User findByName(String name);

    @Transaction
    @Insert
    void insertAll(User... Users);

    @Delete
    void delete(User User);
}
