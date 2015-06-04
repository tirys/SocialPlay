package br.edu.fatecriopreto.projetoandoid.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Usuario;

/**
 * Created by Jéssica on 04/06/2015.
 */
public class DBadapter {
    private SQLiteDatabase database;
    private DBhelper dbHelper;
    private String[] colunas = {DBhelper.ID,
            DBhelper.USUARIO, DBhelper.SENHA, DBhelper.EMAIL,
            DBhelper.NOME, DBhelper.DESCRICAO, DBhelper.LOCAL, DBhelper.JOGOPREFERIDO};

    public  DBadapter(Context context){
        dbHelper = new DBhelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public void adicionar(int id,String usuario, String senha, String email,String nome,String descricao, String local, String jogopreferido){
        ContentValues contentValues =
                new ContentValues();

        contentValues.put(DBhelper.ID, id);
        contentValues.put(DBhelper.USUARIO,usuario);
        contentValues.put(DBhelper.SENHA,senha);
        contentValues.put(DBhelper.EMAIL, email);
        contentValues.put(DBhelper.NOME, nome);
        contentValues.put(DBhelper.DESCRICAO, descricao);
        contentValues.put(DBhelper.LOCAL, local);
        contentValues.put(DBhelper.JOGOPREFERIDO, jogopreferido);

        database.insert(DBhelper.TABELA,null,
                contentValues);

    }

    public Cursor getUsuario(){
        Cursor cursor = database.rawQuery(
                " select id,usuario,senha from "
                        + DBhelper.TABELA, null);

        return cursor;
    }

    private Usuario cursorUsuario(Cursor cursor){
        Usuario usuario =
                new Usuario(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        "",
                        "",0,"","","");
        return usuario;
    }

    public Usuario getUsuario(long id){
        Cursor cursor = database.query(DBhelper.TABELA,colunas, DBhelper.ID + " = " +id, null,null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount() == 0) {
            Usuario usuario =
                    new Usuario(1,
                            "",
                            "",
                            "",
                            "",0,"","","");
            return usuario;
        }
            return cursorUsuario(cursor);

    }



    public void apagar(long idusuario){
        database.delete(DBhelper.TABELA, DBhelper.ID + " = " + idusuario, null);
    }
    public void editar(int id,String usuario, String senha, String email,String nome,String descricao, String local, String jogopreferido){

        ContentValues valores = new ContentValues();


        valores.put(DBhelper.ID, 1);
        valores.put(DBhelper.USUARIO,usuario);
        valores.put(DBhelper.SENHA,senha);
        valores.put(DBhelper.EMAIL, "");
        valores.put(DBhelper.NOME, "");
        valores.put(DBhelper.DESCRICAO, "");
        valores.put(DBhelper.LOCAL, "");
        valores.put(DBhelper.JOGOPREFERIDO, "");

int att;
         att = database.update(DBhelper.TABELA, valores,
                DBhelper.ID + " = ?", new String[]{String.valueOf(1)});

            if (att==0){
                adicionar(1,usuario,senha,"","","","","");
            }
    }

}
