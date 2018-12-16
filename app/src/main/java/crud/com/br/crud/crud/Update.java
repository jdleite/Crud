package crud.com.br.crud.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import crud.com.br.crud.constants.PessoaConstantes;
import crud.com.br.crud.tabelas.Pessoa;

public class Update {

    public void updatePessoa(Pessoa pessoa){
        SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PessoaConstantes.pessoaConstantes.PESSOA_ID,pessoa.getId());
        cv.put(PessoaConstantes.pessoaConstantes.PESSOA_NOME,pessoa.getNome());
        cv.put(PessoaConstantes.pessoaConstantes.PESSOA_IDADE,pessoa.getIdade());
        cv.put(PessoaConstantes.pessoaConstantes.PESSOA_DT_NASC,pessoa.getDtNascimento());

        String where = " ID = '" + pessoa.getId() + "'";

        db.update(MainDb.TABELA_PESSOA,cv,where,null);
    }
}
