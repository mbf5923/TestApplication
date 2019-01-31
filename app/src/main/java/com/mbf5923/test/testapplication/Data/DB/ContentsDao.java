package com.mbf5923.test.testapplication.Data.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mbf5923.test.testapplication.Data.Contents;
import com.mbf5923.test.testapplication.Data.Detail;

import java.util.List;

import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface ContentsDao {
    @Query("SELECT * FROM contents")
    Single<List<Contents>> getAllContents();

    @Insert(onConflict = REPLACE)
    long[] insertContents(List<Contents> content);

    @Query("SELECT * FROM contents where id=:id")
    Single<Detail> getOne(int id);
}
