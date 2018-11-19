package com.saifi369.olschoolnotes.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.saifi369.olschoolnotes.database.AppRepository;
import com.saifi369.olschoolnotes.database.NoteEntity;
import com.saifi369.olschoolnotes.model.NotesAdapter;
import com.saifi369.olschoolnotes.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotesList;
    private AppRepository mRepository;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository=AppRepository.getInstance(application.getApplicationContext());
        mNotesList=mRepository.mNotesList;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllData() {
        mRepository.deleteAllData();
    }

    public void deleteNote(NoteEntity noteEntity) {
        mRepository.deleteNote(noteEntity);
    }
}
