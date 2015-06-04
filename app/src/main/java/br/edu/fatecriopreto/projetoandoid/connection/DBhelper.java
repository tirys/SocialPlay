package br.edu.fatecriopreto.projetoandoid.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jéssica on 04/06/2015.
 */
public class DBhelper extends SQLiteOpenHelper{

    public static String NOME_BANCO = "socialplay.db";
    public static int VERSAO_BANCO = 1;

    public static String ID     = "id";
    public static String USUARIO  = "usuario";
    public static String SENHA = "senha";
    public static String EMAIL  = "email";
    public static String NOME    = "nome";
    //public static String IMAGEM    = "imagem";
    public static String DESCRICAO    = "descricao";
    public static String LOCAL    = "local";
    public static String JOGOPREFERIDO    = "jogopreferido";

    public static String TABELA = "usuario";

    public static String CREATE_DATABASE =
            "create table " + TABELA + " ( " +
                    ID + " integer primary key, "+
                    USUARIO + " text, " +
                    SENHA + " text, " +
                    EMAIL + " text, " +
                    NOME + " text, " +
                    DESCRICAO + " text, " +
                    LOCAL + " text, " +
                    JOGOPREFERIDO + " text);";


    public static String DROP_DATABASE =
            " drop table if exists " + TABELA;

    public DBhelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DATABASE);
        onCreate(db);
    }
}
