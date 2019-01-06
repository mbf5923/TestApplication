package com.mbf5923.test.testapplication.Data.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mbf5923.test.testapplication.Data.Contents;

@Database(entities = {Contents.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ContentsDao contentsDao();

}