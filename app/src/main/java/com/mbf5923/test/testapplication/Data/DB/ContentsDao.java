package com.mbf5923.test.testapplication.Data.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mbf5923.test.testapplication.Data.Contents;
import com.mbf5923.test.testapplication.Data.Detail;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
public interface ContentsDao {
    @Query("SELECT * FROM contents")
    Single<List<Contents>> getAllContents();

    @Insert(onConflict = REPLACE)
    long[] insertContents(List<Contents> content);

    @Query("SELECT * FROM contents where id=:id")
    Single<Detail> getOne(int id);
}
