package com.saifi369.olschoolnotes.database;

import android.content.Context;
import android.util.Log;

import com.saifi369.olschoolnotes.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {


    private static AppRepository ourInstance;
    private AppDatabase mDatabase;

    public List<NoteEntity> mNotesList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public static AppRepository getInstance(Context context) {
        return ourInstance = new AppRepository(context);
    }

    private AppRepository(Context context) {
        mNotesList=SampleDataProvider.getSampleData();
        mDatabase=AppDatabase.getInstance(context);

    }

    public void addSampleData() {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.notesDao().insertAll(mNotesList);
            }
        });

    }
}
