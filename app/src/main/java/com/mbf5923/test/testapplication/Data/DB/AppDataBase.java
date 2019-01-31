package com.mbf5923.test.testapplication.Data.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mbf5923.test.testapplication.Data.Contents;

@Database(entities = {Contents.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ContentsDao contentsDao();

}