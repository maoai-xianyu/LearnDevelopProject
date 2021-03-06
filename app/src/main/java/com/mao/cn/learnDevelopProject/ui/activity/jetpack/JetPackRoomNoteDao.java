package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 * <p>
 * 定义各种的sql查询,执行数据库的各种操作
 */

@Dao
public interface JetPackRoomNoteDao {


    @Insert
    void insert(JetPackRoomNote note);

    @Query("SELECT * FROM notes")
    LiveData<List<JetPackRoomNote>> getAllNotes();


    @Query("SELECT * FROM notes WHERE id=:noteId")
    LiveData<JetPackRoomNote> getNote(String noteId);

    @Update
    void update(JetPackRoomNote roomNote);

    @Delete
    void delete(JetPackRoomNote roomNote);

}
