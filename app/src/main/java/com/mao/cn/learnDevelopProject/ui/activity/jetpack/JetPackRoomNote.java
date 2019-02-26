package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 * <p>
 * 实体类，表名，不定义会有自己的定义规则 default
 * <p>
 * 1. 表面区分大小写
 */

@Entity(tableName = "notes")
public class JetPackRoomNote {

    @PrimaryKey
    @NonNull
    private String id;

    // 对表的列进行定义
    @NonNull
    @ColumnInfo(name = "note")
    private String mNote;

    public JetPackRoomNote(@NonNull String id, @NonNull String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }

    public void setmNote(@NonNull String mNote) {
        this.mNote = mNote;
    }
}
