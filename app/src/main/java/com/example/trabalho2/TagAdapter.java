package com.example.trabalho2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private Cursor cursor;
    private TagAdapter.OnItemClickListener listener;

    public TagAdapter(Cursor c) {
        this.cursor = c;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(TagAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public void setCursor(Cursor c) {
        this.cursor = c;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tagView = inflater.inflate(R.layout.tag_layout, parent, false);
        TagAdapter.ViewHolder holder = new TagAdapter.ViewHolder(tagView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.ViewHolder holder, int position) {
        int idxTag = cursor.getColumnIndexOrThrow(AgendaContract.Tag.COLUMN_TITULO);

        cursor.moveToPosition(position);

        holder.itemTag.setText(cursor.getString(idxTag));
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemTag;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemTag = itemView.findViewById(R.id.textTag);

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
