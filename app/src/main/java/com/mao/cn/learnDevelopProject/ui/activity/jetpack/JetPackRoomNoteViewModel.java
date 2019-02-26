package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackRoomNoteViewModel extends AndroidViewModel {


    private String TAG = this.getClass().getSimpleName();

    private JetPackRoomNoteDao roomNoteDao;
    private JetPackRoomNoteDatabase noteDatabase;
    private LiveData<List<JetPackRoomNote>> mAllNotes;

    public JetPackRoomNoteViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = JetPackRoomNoteDatabase.getDataBase(application);
        roomNoteDao = noteDatabase.roomNoteDao();
        mAllNotes = roomNoteDao.getAllNotes();
    }

    // 包装类插入
    public void insert(JetPackRoomNote roomNote) {
        new InsertAsyncTask(roomNoteDao).execute(roomNote);
    }

    // 查询
    LiveData<List<JetPackRoomNote>> getAllNotes() {
        return mAllNotes;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogU.i(TAG + " viewmodel destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<JetPackRoomNote, Void, Void> {
        JetPackRoomNoteDao roomNoteDao;

        public InsertAsyncTask(JetPackRoomNoteDao roomNoteDao) {
            this.roomNoteDao = roomNoteDao;
        }

        @Override
        protected Void doInBackground(JetPackRoomNote... jetPackRoomNotes) {
            // 插入数据
            roomNoteDao.insert(jetPackRoomNotes[0]);
            return null;
        }


    }
}
