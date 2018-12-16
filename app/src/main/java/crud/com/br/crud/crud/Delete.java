package crud.com.br.crud.crud;

import android.database.sqlite.SQLiteDatabase;

import crud.com.br.crud.tabelas.Pessoa;

public class Delete {

    public void removerTabela(){
        try {

            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            String query = "DROP TABLE IF EXISTS " + MainDb.TABELA_PESSOA;
            db.execSQL(query);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void removerPessoa(Pessoa pessoa){
        try {
            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            String query = " ID = '"+ pessoa.getId()+"'";
            db.delete(MainDb.TABELA_PESSOA,query,null);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
