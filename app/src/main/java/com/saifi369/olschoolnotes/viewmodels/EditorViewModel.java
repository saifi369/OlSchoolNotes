package com.saifi369.olschoolnotes.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.saifi369.olschoolnotes.database.AppRepository;
import com.saifi369.olschoolnotes.database.NoteEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote=new MutableLiveData<>();
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private AppRepository mRepository;


    public EditorViewModel(@NonNull Application application) {
        super(application);

        mRepository=AppRepository.getInstance(application.getApplicationContext());
    }

    public void loadNote(final int noteId) {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity noteEntity=mRepository.loadNote(noteId);
                mLiveNote.postValue(noteEntity);

            }
        });

    }

    public void saveAndExit(String noteText) {

        NoteEntity noteEntity=mLiveNote.getValue();

        if (noteEntity == null) {

            if(TextUtils.isEmpty(noteText.trim())){
                return;
            }else {
                noteEntity = new NoteEntity(new Date(),noteText.trim());
            }

        }else{
            noteEntity.setText(noteText.trim());
            noteEntity.setDate(new Date());
        }
        mRepository.insertNote(noteEntity);

    }
}
