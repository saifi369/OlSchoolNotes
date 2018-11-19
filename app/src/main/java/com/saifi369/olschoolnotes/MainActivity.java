package com.saifi369.olschoolnotes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.saifi369.olschoolnotes.database.NoteEntity;
import com.saifi369.olschoolnotes.model.NotesAdapter;
import com.saifi369.olschoolnotes.utils.SampleDataProvider;
import com.saifi369.olschoolnotes.viewmodels.ListActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<NoteEntity> mNotesList;
    private ListActivityViewModel mViewModel;

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

        initViewModel();

        ButterKnife.bind(this);
        initRecyclerView();

        mNotesList=mViewModel.mNotesList;
        showData();

    }

    private void initViewModel() {

        mViewModel=ViewModelProviders.of(this)
                .get(ListActivityViewModel.class);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){
            case R.id.add_sample_data:{
                addSampleData();
                return  true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
