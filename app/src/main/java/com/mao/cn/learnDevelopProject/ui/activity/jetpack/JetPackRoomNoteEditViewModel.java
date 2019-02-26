package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackRoomNoteEditViewModel extends AndroidViewModel {


    private String TAG = this.getClass().getSimpleName();

    private JetPackRoomNoteDao roomNoteDao;
    private JetPackRoomNoteDatabase noteDatabase;

    public JetPackRoomNoteEditViewModel(@NonNull Application application) {
        super(application);
        LogU.i(TAG + " edit viewmodel");
        noteDatabase = JetPackRoomNoteDatabase.getDataBase(application);
        roomNoteDao = noteDatabase.roomNoteDao();
    }

    // 根据id 查询数据
    public LiveData<JetPackRoomNote> getNoteFromId(String noteId) {
        return roomNoteDao.getNote(noteId);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        LogU.i(TAG + " viewmodel destroyed");
    }

}
