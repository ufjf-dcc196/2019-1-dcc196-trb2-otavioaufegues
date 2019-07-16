package com.example.trabalho2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    AgendaDBHelper dbhelper;
    TarefaAdapter adapter;
    SQLiteDatabase sqLiteDatabase;
    String[] visao = {
            AgendaContract.Tarefa._ID,
            AgendaContract.Tarefa.COLUMN_TITULO,
            AgendaContract.Tarefa.COLUMN_DESCRICAO,
            AgendaContract.Tarefa.COLUMN_DT_LIMITE,
            AgendaContract.Tarefa.COLUMN_DIFICULDADE,
            AgendaContract.Tarefa.COLUMN_UPDATED_AT,
            AgendaContract.Tarefa.COLUMN_STATUS
    };

    public static final int TAREFA = 1;
    public static final int NOVA_TAREFA = 2;
    public static final int TAGS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listaTarefas = findViewById(R.id.lista_tarefas);
        Button btnNovaTarefa = findViewById(R.id.btn_nova_tarefa);
        Button btnNovaTag = findViewById(R.id.btn_nova_tag);

        dbhelper = new AgendaDBHelper(this);
        sqLiteDatabase = dbhelper.getReadableDatabase();

        Cursor c = sqLiteDatabase.query(AgendaContract.Tarefa.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new TarefaAdapter(c);
        listaTarefas.setAdapter(adapter);
        listaTarefas.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new TarefaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                TextView txtTitulo = (TextView) itemView.findViewById(R.id.textTitulo);
                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("titulo", txtTitulo.getText().toString());

                startActivityForResult(intent, TAREFA);

            }
        });

        btnNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivityForResult(intent, NOVA_TAREFA);

            }
        });

//        btnGerenciarTags.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, GerenciarTagsActivity.class);
//                startActivityForResult(intent, GERENCIAR_TAGS);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.setCursor(sqLiteDatabase.query(AgendaContract.Tarefa.TABLE_NAME, visao, null, null, null, null, null));
        adapter.notifyDataSetChanged();
        switch (requestCode) {
            case NOVA_TAREFA:
                adapter.notifyDataSetChanged();
                break;
            case TAREFA:
                break;
            default:
                break;
        }
    }
}
