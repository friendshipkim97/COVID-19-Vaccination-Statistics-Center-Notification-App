package com.example.mobileprogrammingproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mobileprogrammingproject.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract UserDao userDao();
   private static AppDatabase mAppDatabase;

   public static AppDatabase getInstance(Context context) {
      if(mAppDatabase==null){
         mAppDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "mobileDB")
                 .createFromAsset("database/mobile-db.db")
                 .allowMainThreadQueries()
                 .build();
      }
      return mAppDatabase;
   }
}
