package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

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
}
