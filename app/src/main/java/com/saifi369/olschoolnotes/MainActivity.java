package com.saifi369.olschoolnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.saifi369.olschoolnotes.model.NoteEntity;
import com.saifi369.olschoolnotes.model.NotesAdapter;
import com.saifi369.olschoolnotes.utils.SampleDataProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<NoteEntity> mNotesList;

    @BindView(R.id.notes_recyclerview)
    RecyclerView mRecyclerView;

    @OnClick(R.id.fab_add_note)
    void onFabClicked(){
        Intent intent=new Intent(MainActivity.this,EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();

        mNotesList=SampleDataProvider.getSampleData();
        showData();

    }

    private void showData() {
        NotesAdapter notesAdapter=new NotesAdapter(this,mNotesList);
        mRecyclerView.setAdapter(notesAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.hasFixedSize();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
