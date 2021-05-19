package com.example.mobileprogrammingproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobileprogrammingproject.model.QnA;
import com.example.mobileprogrammingproject.model.User;

@Database(entities = {User.class, QnA.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract UserDao userDao();
   public abstract QnADao qnADao();
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
