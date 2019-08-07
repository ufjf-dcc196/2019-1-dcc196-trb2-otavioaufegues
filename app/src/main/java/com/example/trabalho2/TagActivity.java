package com.example.trabalho2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TagActivity extends AppCompatActivity {
    AgendaDBHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;

    TagTarefaAdapter adapter;

    String[] visao = {
            AgendaContract.Tag._ID,
            AgendaContract.Tag.COLUMN_TITULO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        RecyclerView recyclerViewTagTarefa = findViewById(R.id.recyclerViewTagTarefa);

        dbhelper = new AgendaDBHelper(this);
        sqLiteDatabase = dbhelper.getReadableDatabase();


        Cursor c = sqLiteDatabase.query(AgendaContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new TagTarefaAdapter(c, getIntent().getIntExtra("id", -1), this);
        recyclerViewTagTarefa.setAdapter(adapter);
        recyclerViewTagTarefa.setLayoutManager(new LinearLayoutManager(this));


    }
}
