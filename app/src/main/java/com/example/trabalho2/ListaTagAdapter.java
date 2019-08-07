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

public class ListaTagAdapter extends RecyclerView.Adapter<ListaTagAdapter.ViewHolder> {

    private Cursor cursor;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ListaTagAdapter(Cursor c) {
        this.cursor = c;
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListaTagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.lista_tag_layout, parent, false);
        ViewHolder holder = new ViewHolder(tarefaView);
        return holder;

    }


    @Override
    public void onBindViewHolder(@NonNull ListaTagAdapter.ViewHolder holder, int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(AgendaContract.Tag.COLUMN_TITULO);


        cursor.moveToPosition(position);

        holder.txtTitulo.setText(cursor.getString(idxTitulo));


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitulo;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.textViewTag);

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
