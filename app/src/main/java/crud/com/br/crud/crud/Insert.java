package crud.com.br.crud.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import crud.com.br.crud.tabelas.Pessoa;

public class Insert {

    public void addPessoa(Pessoa pessoa){

        try {
            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("NOME",pessoa.getNome());
            cv.put("IDADE",pessoa.getIdade());
            cv.put("DT_NASC",pessoa.getDtNascimento());

            db.insert(MainDb.TABELA_PESSOA,null,cv);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
