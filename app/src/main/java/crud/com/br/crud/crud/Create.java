package crud.com.br.crud.crud;

import android.database.sqlite.SQLiteDatabase;

public class Create {


    public void createTable(){
        try {
            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            String colunas = "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME VARCHAR,IDADE INTEGER,DT_NASC VARCHAR)";
            String query = "CREATE TABLE IF NOT EXISTS " + MainDb.TABELA_PESSOA + colunas;
            db.execSQL(query);

        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
