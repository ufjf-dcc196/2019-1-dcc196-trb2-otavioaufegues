package com.example.trabalho2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {

    private Cursor cursor;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TarefaAdapter(Cursor c) {
        this.cursor = c;
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.tarefa_layout, parent, false);
        ViewHolder holder = new ViewHolder(tarefaView);
        return holder;

    }


    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.ViewHolder holder, int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DESCRICAO);
        int idxDificuldade = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DIFICULDADE);
        int idxDtLimite = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DT_LIMITE);
        int idxUpdatedAt = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_UPDATED_AT);
        int idxStatus = cursor.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_STATUS);

        cursor.moveToPosition(position);

        holder.txtTitulo.setText(cursor.getString(idxTitulo));
        holder.txtDescricao.setText(cursor.getString(idxDescricao));
        holder.txtDtLimite.setText(cursor.getString(idxDificuldade));
        holder.txtUpdatedAt.setText(cursor.getString(idxDtLimite));
        holder.txtDificuldade.setText(cursor.getString(idxUpdatedAt));
        holder.txtStatus.setText(cursor.getString(idxStatus));

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitulo, txtDescricao, txtDtLimite, txtUpdatedAt, txtDificuldade, txtStatus;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.textTitulo);
            txtDescricao = itemView.findViewById(R.id.textDescricao);
            txtDtLimite = itemView.findViewById(R.id.textDtLimite);
            txtUpdatedAt = itemView.findViewById(R.id.textUpdatedAt);
            txtDificuldade = itemView.findViewById(R.id.textDificuldade);
            txtStatus = itemView.findViewById(R.id.textStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
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
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(v, position);
            }
        }
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

}
