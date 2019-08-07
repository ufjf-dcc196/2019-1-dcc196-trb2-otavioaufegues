package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagTarefaAdapter extends RecyclerView.Adapter<TagTarefaAdapter.ViewHolder>{
    private Cursor cursor;
    Context context;
    AgendaDBHelper dbhelper;
    SQLiteDatabase db;
    private int tarefaID;
    private TagTarefaAdapter.OnItemClickListener listener;


    public TagTarefaAdapter(Cursor c, int tid, Context con) {
        this.cursor = c;
        this.tarefaID = tid;
        this.context = con;
        dbhelper = new AgendaDBHelper(this.context);
        db = dbhelper.getWritableDatabase();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setCursor(Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(TagTarefaAdapter.OnItemClickListener listener) { this.listener = listener; }


    @NonNull
    @Override
    public TagTarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.tag_tarefa_layout, parent, false);
        TagTarefaAdapter.ViewHolder viewHolder = new TagTarefaAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagTarefaAdapter.ViewHolder holder, final int position) {
        int idxID = cursor.getColumnIndexOrThrow(AgendaContract.Tag._ID);
        int idxTag = cursor.getColumnIndexOrThrow(AgendaContract.Tag.COLUMN_TITULO);
        String[] visao = {AgendaContract.TagTarefa.COLUMN_ID_TAG, AgendaContract.TagTarefa.COLUMN_ID_TAREFA};

        cursor.moveToPosition(position);

        holder.checkboxTag.setText(cursor.getString(idxTag));

        String select = AgendaContract.TagTarefa.COLUMN_ID_TAG + " = ? AND " +
                AgendaContract.TagTarefa.COLUMN_ID_TAREFA + " = ?";
        String[] selectArgs = {cursor.getString(idxID), String.valueOf(tarefaID)};
        Cursor c = db.query(AgendaContract.TagTarefa.TABLE_NAME, visao, select, selectArgs, null, null, null);
        //Toast.makeText(context, cursor.getString(idxID) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

        if (c.getCount() != 0){
            holder.checkboxTag.setChecked(true);
        }

        holder.checkboxTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(context, cursor.getString(cursor.getColumnIndexOrThrow(AgendaContract.Tag._ID)) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

                String[] visao = {AgendaContract.Tag._ID, AgendaContract.Tag.COLUMN_TITULO};
                Cursor inside = db.query(AgendaContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
                inside.moveToPosition(position);
                //Toast.makeText(context, inside.getString(inside.getColumnIndexOrThrow(AgendaContract.Tag._ID)) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

                if (isChecked){
                    ContentValues values = new ContentValues();
                    values.put(AgendaContract.TagTarefa.COLUMN_ID_TAG, inside.getString(inside.getColumnIndexOrThrow(AgendaContract.Tag._ID)));
                    values.put(AgendaContract.TagTarefa.COLUMN_ID_TAREFA, String.valueOf(tarefaID));

                    db.insert(AgendaContract.TagTarefa.TABLE_NAME, null, values);
                } else {
                    String select = AgendaContract.TagTarefa.COLUMN_ID_TAG + " = ? AND " +
                            AgendaContract.TagTarefa.COLUMN_ID_TAREFA + " = ?";
                    String[] selectArgs = {inside.getString(inside.getColumnIndexOrThrow(AgendaContract.Tag._ID)), String.valueOf(tarefaID)};
                    db.delete(AgendaContract.TagTarefa.TABLE_NAME, select, selectArgs);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CheckBox checkboxTag;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            checkboxTag = itemView.findViewById(R.id.checkboxTagTarefa);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position !=RecyclerView.NO_POSITION){
                listener.onItemClick(v, position);
            }
        }

    }
}
