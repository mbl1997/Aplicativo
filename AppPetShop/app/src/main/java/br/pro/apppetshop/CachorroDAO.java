package br.pro.apppetshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class CachorroDAO {

    public static void inserir(Context context, Cachorro cachorro){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", cachorro.getNome() );
        valores.put("idade", cachorro.getIdade() );

        db.insert("cachorro", null, valores);
    }

    public static void editar(Context context, Cachorro cachorro){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", cachorro.getNome() );
        valores.put("idade", cachorro.getIdade() );

        db.update("cachorro", valores ,
                " id = " + cachorro.getId(), null  );
    }

    public static void excluir(Context context, int idCachorro){
        SQLiteDatabase db = new Conexao(context).getWritableDatabase();

        db.delete("cachorro",
                " id = " + idCachorro, null  );
    }

    public static List<Cachorro> getCachorro(Context context){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        List<Cachorro> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, nome, idade FROM cachorro ORDER BY nome",
                null);
        if( cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
               Cachorro cachorro = new Cachorro();
                cachorro.setId( cursor.getInt( 0 )  );
                cachorro.setNome( cursor.getString( 1 )  );
                cachorro.setIdade( cursor.getString( 2 )  );
                lista.add( cachorro );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Cachorro getAlunoById(Context context, int idCachorro){
        SQLiteDatabase db = new Conexao(context).getReadableDatabase();
        Cachorro cachorro = null;
        Cursor cursor = db.rawQuery("SELECT id, nome, idade FROM cachorro " +
                " WHERE id = " + idCachorro, null);
        if( cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            cachorro = new Cachorro();
            cachorro.setId( cursor.getInt( 0 )  );
            cachorro.setNome( cursor.getString( 1 )  );
            cachorro.setIdade( cursor.getString( 2 )  );
        }
        return cachorro;
    }
}
