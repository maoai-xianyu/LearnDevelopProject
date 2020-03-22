package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 * 数据库
 * 1. 必须是单例的
 */
@Database(entities = JetPackRoomNote.class, version = 1)
public abstract class JetPackRoomNoteDatabase extends RoomDatabase {

    public abstract JetPackRoomNoteDao roomNoteDao();

    // 单例
    private static volatile JetPackRoomNoteDatabase roodNoteInstance;

    static JetPackRoomNoteDatabase getDataBase(final Context context) {
        if (roodNoteInstance == null) {
            synchronized (JetPackRoomNoteDatabase.class) {
                if (roodNoteInstance == null) {
                    roodNoteInstance = Room.databaseBuilder(context.getApplicationContext(),
                            JetPackRoomNoteDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return roodNoteInstance;
    }
}
