package com.example.roomimplementation;

import android.app.Application;

import androidx.room.Room;

import com.example.roomimplementation.DbUtils.LocalDB;

public class RoomImplementation extends Application {
    private RoomImplementation mInstance;
    private LocalDB dbInstance;

    @Override
    public void onCreate() {
        super.onCreate( );

        mInstance = this;
        dbInstance = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "LOCALDB").build();
    }

    public static RoomImplementation getmInstance() {
        return mInstance;
    }

    public LocalDB getDbInstance() {
        return dbInstance;
    }


}
