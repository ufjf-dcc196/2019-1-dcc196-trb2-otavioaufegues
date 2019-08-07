package com.example.trabalho2;

import android.provider.BaseColumns;

public final class AgendaContract {

    public static final class Tarefa implements BaseColumns{

        public static final String TABLE_NAME = "tarefa";
        public static final String COLUMN_TITULO =  "titulo_tarefa";
        public static final String COLUMN_DESCRICAO = "descricao_tarefa";
        public static final String COLUMN_DIFICULDADE = "dificuldade";
        public static final String COLUMN_DT_LIMITE = "dt_limite";
        public static final String COLUMN_UPDATED_AT = "updated_at";
        public static final String COLUMN_STATUS = "status";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME, _ID, COLUMN_TITULO, COLUMN_DESCRICAO, COLUMN_DIFICULDADE,
                COLUMN_DT_LIMITE, COLUMN_UPDATED_AT, COLUMN_STATUS
        );
        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);
    }

    public static final class Tag implements BaseColumns{

        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_TITULO = "titulo_tag";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT)",
                TABLE_NAME, _ID, COLUMN_TITULO
        );
        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);
    }

    public static final class TagTarefa implements BaseColumns{
        public static final String TABLE_NAME = "tarefa_tag";
        public static final String COLUMN_ID_TAREFA =  "id_tarefa";
        public static final String COLUMN_ID_TAG = "id_tag";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER," +
                "%s INTEGER)",TABLE_NAME,_ID, COLUMN_ID_TAREFA,COLUMN_ID_TAG
        );
        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);

    }

    public static class Status implements BaseColumns {
        public static final String TABLE_NAME = "status";
        public static final String COLUMN_TITULO = "titulo_status";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT)", TABLE_NAME, _ID, COLUMN_TITULO
        );
        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);
    }

}
