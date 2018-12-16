package crud.com.br.crud.crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import crud.com.br.crud.tabelas.Pessoa;

public class Read {




    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        SQLiteDatabase db = MainDb.getInstancia().getReadableDatabase();

        String query = "SELECT * FROM " + MainDb.TABELA_PESSOA + " ORDER BY ID DESC";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(c.getInt(0));
                pessoa.setNome(c.getString(1));
                pessoa.setIdade(c.getInt(2));
                pessoa.setDtNascimento(c.getString(3));
                pessoas.add(pessoa);

            } while (c.moveToNext());
        }

        c.close();
        return pessoas;




    }




}
