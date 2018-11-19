package com.saifi369.olschoolnotes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.saifi369.olschoolnotes.database.NoteEntity;
import com.saifi369.olschoolnotes.utils.Constants;
import com.saifi369.olschoolnotes.viewmodels.EditorViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    @BindView(R.id.edit_note_text)
    TextView mEditText;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel() {

        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {

                if (noteEntity != null) {
                    mEditText.setText(noteEntity.getText());
                }
            }
        });

        Bundle bundle=getIntent().getExtras();

        if (bundle == null) {
            setTitle("New Note");
            mNewNote=true;
        }else{
            setTitle("Edit Note");
            int noteId=bundle.getInt(Constants.NOTE_ID_KEY);
            mViewModel.loadNote(noteId);
            mNewNote=false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!mNewNote){
            getMenuInflater().inflate(R.menu.menu_editor,menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            saveAndExit();
            return true;
        }else if(item.getItemId() == R.id.action_delete_note){
            deleteNote();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        mViewModel.deleteNote();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAndExit();
    }

    private void saveAndExit() {
        mViewModel.saveAndExit(mEditText.getText().toString());
        finish();
    }
}
