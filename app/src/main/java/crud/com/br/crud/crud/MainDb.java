package crud.com.br.crud.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MainDb extends SQLiteOpenHelper {

    private  static String NOME_BANCO = "BRUNO";
    private static int VERSAO_BANCO = 1;
    public static String TABELA_PESSOA = "PESSOA";

    private static MainDb instancia;

    public MainDb() {
        super(MyApp.getContext(), NOME_BANCO, null, VERSAO_BANCO);
    }

    public static MainDb getInstancia(){
        if(instancia == null)instancia = new MainDb();
        return instancia;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        instancia = null;
        super.close();
    }
}
