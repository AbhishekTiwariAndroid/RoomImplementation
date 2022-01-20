package com.example.roomimplementation.DbUtils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void createuser (User user);


    @Query("SELECT * FROM user where username like :strUsername")
    User getUserByUsername(String strUsername);

}
