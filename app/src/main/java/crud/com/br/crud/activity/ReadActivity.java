package crud.com.br.crud.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import crud.com.br.crud.R;
import crud.com.br.crud.constants.PessoaConstantes;
import crud.com.br.crud.crud.Create;
import crud.com.br.crud.crud.Read;
import crud.com.br.crud.tabelas.Pessoa;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder viewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        new Create().createTable();


        viewHolder.nome = findViewById(R.id.edtBuscar);
        viewHolder.buscar = findViewById(R.id.btnBuscar);
        viewHolder.lista = findViewById(R.id.lstNome);
        viewHolder.buscar = findViewById(R.id.btnBuscar);


        viewHolder.pessoas = new Read().getPessoas();

        viewHolder.nomes = new ArrayList<>();
        viewHolder.idades = new ArrayList<>();
        viewHolder.dtNasc = new ArrayList<>();
        viewHolder.ids = new ArrayList<>();

        for (int i = 0; i < viewHolder.pessoas.size(); i++) {
            Pessoa p = viewHolder.pessoas.get(i);


            viewHolder.ids.add(p.getId());
            viewHolder.nomes.add(p.getNome());
            viewHolder.idades.add(p.getIdade());
            viewHolder.dtNasc.add(p.getDtNascimento());

        }

        carregarListView();
        buscar();
        click();


    }

    public void buscar() {


        viewHolder.buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.nomes = new ArrayList<>();
                viewHolder.idades = new ArrayList<>();
                viewHolder.dtNasc = new ArrayList<>();
                viewHolder.ids = new ArrayList<>();


                for (int i = 0; i < viewHolder.pessoas.size(); i++) {

                    final Pessoa p = viewHolder.pessoas.get(i);

                    if (p.getNome().equalsIgnoreCase(viewHolder.nome.getText().toString())) {
                        viewHolder.nomes.add(p.getNome());
                        viewHolder.idades.add(p.getIdade());
                        viewHolder.dtNasc.add(p.getDtNascimento());
                        viewHolder.ids.add(p.getId());


                        carregarListView();
                        click();

                    }


                }


            }
        });


    }

    public void carregarListView() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, viewHolder.nomes);
        viewHolder.lista.setAdapter(arrayAdapter);

    }

    public void click() {
        viewHolder.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int ident = viewHolder.ids.get(position);
                String nome = viewHolder.nomes.get(position);
                int idade = viewHolder.idades.get(position);
                String data_nasc = viewHolder.dtNasc.get(position);


                Intent intent = new Intent(ReadActivity.this, CreateActivity.class);
                Bundle b = new Bundle();


                b.putInt(PessoaConstantes.pessoaConstantes.PESSOA_ID, ident);
                b.putString(PessoaConstantes.pessoaConstantes.PESSOA_NOME, nome);
                b.putInt(PessoaConstantes.pessoaConstantes.PESSOA_IDADE, idade);
                b.putString(PessoaConstantes.pessoaConstantes.PESSOA_DT_NASC, data_nasc);


                intent.putExtras(b);

                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {

        private EditText nome;
        private Button buscar;
        private ArrayList<Pessoa> pessoas;

        private ListView lista;
        private ArrayList<String> nomes;
        private ArrayList<String> dtNasc;
        private ArrayList<Integer> idades;
        private ArrayList<Integer> ids;

    }
}
