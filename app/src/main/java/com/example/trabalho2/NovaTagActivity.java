package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NovaTagActivity extends AppCompatActivity {
    AgendaDBHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;
    ListaTagAdapter adapter;
    String[] visao = {
            AgendaContract.Tag._ID,
            AgendaContract.Tag.COLUMN_TITULO
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tag);

        final AgendaDBHelper agendaHelper = new AgendaDBHelper(this);

        final EditText nome_tag = findViewById(R.id.editTag);

        Button btnSaveNovaTag = findViewById(R.id.buttonNovaTag);

        btnSaveNovaTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = agendaHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(AgendaContract.Tag.COLUMN_TITULO, nome_tag.getText().toString());

                long id = db.insert(AgendaContract.Tag.TABLE_NAME, null, values);
                Toast.makeText(NovaTagActivity.this, "id: " + id, Toast.LENGTH_SHORT).show();

                finish();


            }
        });

        RecyclerView listaTag = findViewById(R.id.lista_tag);

        dbhelper = new AgendaDBHelper(this);
        sqLiteDatabase = dbhelper.getReadableDatabase();

        Cursor c = sqLiteDatabase.query(AgendaContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new ListaTagAdapter(c);
        listaTag.setAdapter(adapter);
        listaTag.setLayoutManager(new LinearLayoutManager(this));
    }
}
