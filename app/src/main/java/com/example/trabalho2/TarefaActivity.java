package com.example.trabalho2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class TarefaActivity extends AppCompatActivity {
    AgendaDBHelper AgendaHelper;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        AgendaHelper = new AgendaDBHelper(this);

        final SQLiteDatabase db = AgendaHelper.getReadableDatabase();
        final String[] visao = {
                AgendaContract.Tarefa._ID,
                AgendaContract.Tarefa.COLUMN_TITULO,
                AgendaContract.Tarefa.COLUMN_DESCRICAO,
                AgendaContract.Tarefa.COLUMN_DT_LIMITE,
                AgendaContract.Tarefa.COLUMN_DIFICULDADE,
                AgendaContract.Tarefa.COLUMN_UPDATED_AT,
                AgendaContract.Tarefa.COLUMN_STATUS,
        };

        final EditText titulo = findViewById(R.id.editTitulo);
        final EditText descricao = findViewById(R.id.editDetDescricao);
        final Spinner dificuldade = findViewById(R.id.spinnerDificuldade);
        final EditText limite = findViewById(R.id.editDetLimite);
        final Spinner estado = findViewById(R.id.spinnerStatus);
        Button btnConfirmar = findViewById(R.id.buttonConfirma);
        Button btnTags = findViewById(R.id.buttonTagsTarefa);


        dificuldade.setAdapter(new ArrayAdapter<Dificuldade>(this, android.R.layout.simple_selectable_list_item, Dificuldade.values()));
        estado.setAdapter(new ArrayAdapter<Status>(this, android.R.layout.simple_selectable_list_item, Status.values()));



        final String selecao = AgendaContract.Tarefa.COLUMN_TITULO + " = ?";
        final String[] args = {getIntent().getStringExtra("titulo")};
        String sort = AgendaContract.Tarefa.COLUMN_TITULO + " DESC";
        Cursor c = db.query(AgendaContract.Tarefa.TABLE_NAME, visao, selecao, args, null, null, sort);

        int idxID = c.getColumnIndexOrThrow(AgendaContract.Tarefa._ID);
        int idxTitulo = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_TITULO);
        int idxDescricao = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DESCRICAO);
        int idxDificuldade = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DIFICULDADE);
        int idxDtLimite = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_DT_LIMITE);
        int idxUpdatedAt = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_UPDATED_AT);
        int idxStatus = c.getColumnIndexOrThrow(AgendaContract.Tarefa.COLUMN_STATUS);

        c.moveToFirst();
        titulo.setText(c.getString(idxTitulo));
        descricao.setText(c.getString(idxDescricao));
        dificuldade.setSelection(Dificuldade.getDificuldade(cursor.getString(idxDificuldade)).getValor());
        limite.setText(c.getString(idxDtLimite));
        estado.setSelection(Status.getStatus(cursor.getString(idxStatus)).getValor());



        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(AgendaContract.Tarefa.COLUMN_TITULO, titulo.getText().toString());
                values.put(AgendaContract.Tarefa.COLUMN_DESCRICAO, descricao.getText().toString());
                values.put(AgendaContract.Tarefa.COLUMN_DIFICULDADE, dificuldade.getSelectedItem().toString());
                values.put(AgendaContract.Tarefa.COLUMN_DT_LIMITE, limite.getText().toString());
                values.put(AgendaContract.Tarefa.COLUMN_UPDATED_AT, Calendar.getInstance().getTime().toString());
                values.put(AgendaContract.Tarefa.COLUMN_STATUS, estado.getSelectedItem().toString());

                long id = db.update(AgendaContract.Tarefa.TABLE_NAME, values, selecao, args);

                finish();
            }
        });

    }
}
