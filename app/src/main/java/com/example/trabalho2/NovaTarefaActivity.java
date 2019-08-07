package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class NovaTarefaActivity extends AppCompatActivity {
    AgendaDBHelper agendaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        agendaHelper = new AgendaDBHelper(this);

        final EditText titulo = findViewById(R.id.editTituloNovo);
        final EditText descricao = findViewById(R.id.editDescricaoNovo);
        final Spinner dificuldade = findViewById(R.id.spinnerDificuldadeNovo);
        final Spinner status = findViewById(R.id.spinnerStatusNovo);
        final EditText dtLimite = findViewById(R.id.editDtLimiteNovo);
        Button btnConfirmar = findViewById(R.id.btnConfirmaNovo);

        dificuldade.setAdapter(new ArrayAdapter<Dificuldade>(this, android.R.layout.simple_selectable_list_item, Dificuldade.values()));
        status.setAdapter(new ArrayAdapter<Status>(this, android.R.layout.simple_selectable_list_item, Status.values()));


        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();
                Tarefa tarefa = new Tarefa(titulo.getText().toString(),
                        Dificuldade.getDificuldade(dificuldade.getSelectedItem().toString()),
                        descricao.getText().toString(),
                        dtLimite.getText().toString()
                );

                SQLiteDatabase db = agendaHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(AgendaContract.Tarefa.COLUMN_TITULO, tarefa.getTitulo());
                values.put(AgendaContract.Tarefa.COLUMN_DESCRICAO, tarefa.getDescricao());
                values.put(AgendaContract.Tarefa.COLUMN_DIFICULDADE, tarefa.getDificuldade().toString());
                values.put(AgendaContract.Tarefa.COLUMN_DT_LIMITE, tarefa.getDt_limite());
                values.put(AgendaContract.Tarefa.COLUMN_UPDATED_AT, Calendar.getInstance().getTime().toString());
                values.put(AgendaContract.Tarefa.COLUMN_STATUS, Status.AFAZER.toString());

                long id = db.insert(AgendaContract.Tarefa.TABLE_NAME, null, values);
                Toast.makeText(NovaTarefaActivity.this, "id: " + id, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }
}
