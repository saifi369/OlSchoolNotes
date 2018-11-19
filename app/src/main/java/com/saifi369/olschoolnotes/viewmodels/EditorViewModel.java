package com.saifi369.olschoolnotes.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.saifi369.olschoolnotes.database.AppRepository;
import com.saifi369.olschoolnotes.database.NoteEntity;

import java.util.List;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();

    private AppRepository mRepository;


    public EditorViewModel(@NonNull Application application) {
        super(application);

        mRepository=AppRepository.getInstance(application.getApplicationContext());
    }
}
