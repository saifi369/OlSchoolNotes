package com.saifi369.olschoolnotes.model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saifi369.olschoolnotes.EditorActivity;
import com.saifi369.olschoolnotes.R;
import com.saifi369.olschoolnotes.database.NoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.saifi369.olschoolnotes.utils.Constants.NOTE_ID_KEY;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context mContext;
    private List<NoteEntity> mDataList;

    public NotesAdapter(Context mContext, List<NoteEntity> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(mContext).inflate(R.layout.note_item_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final NoteEntity noteEntity=mDataList.get(position);
        holder.textView.setText(noteEntity.getText());

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,EditorActivity.class);
                intent.putExtra(NOTE_ID_KEY,noteEntity.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public NoteEntity getNoteAtPosition(int position){
        return mDataList.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.note_text)
        TextView textView;

        @BindView(R.id.fab_edit_note)
        FloatingActionButton fab;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
