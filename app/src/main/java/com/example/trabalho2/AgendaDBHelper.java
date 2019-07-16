package com.example.trabalho2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";

    public AgendaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AgendaContract.Tarefa.CREATE_TABLE);
        sqLiteDatabase.execSQL(AgendaContract.Tag.CREATE_TABLE);
        sqLiteDatabase.execSQL(AgendaContract.TarefaTag.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(AgendaContract.Tarefa.DROP_TABLE);
        sqLiteDatabase.execSQL(AgendaContract.Tag.DROP_TABLE);
        sqLiteDatabase.execSQL(AgendaContract.TarefaTag.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
