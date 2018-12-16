package crud.com.br.crud.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import crud.com.br.crud.R;
import crud.com.br.crud.constants.PessoaConstantes;
import crud.com.br.crud.crud.Delete;
import crud.com.br.crud.crud.Insert;
import crud.com.br.crud.crud.Read;
import crud.com.br.crud.crud.Update;
import crud.com.br.crud.tabelas.Pessoa;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog d;

    private ViewHolder viewHolder = new ViewHolder();


    int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        viewHolder.edt_nome = findViewById(R.id.edtNome);
        viewHolder.edt_idade = findViewById(R.id.edtIdade);
        viewHolder.edt_dt_nasc = findViewById(R.id.edtNascimento);
        viewHolder.cadastrar = findViewById(R.id.btnInserir);
        viewHolder.remover = findViewById(R.id.btnDeletar);
        viewHolder.alterar = findViewById(R.id.btnAlterar);

        viewHolder.remover.setEnabled(false);
        viewHolder.alterar.setEnabled(false);


        listener();
        inserirData();
        getBundle();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInserir) {
            inserir();
        } else if (v.getId() == R.id.btnDeletar) {
            remover();
        } else if (v.getId() == R.id.btnAlterar) {
            alterar();
        }

    }

    private void inserir() {


        if (!verificar()) {
            return;
        }
        Pessoa p = new Pessoa();

        p.setNome(viewHolder.edt_nome.getText().toString());
        p.setIdade(Integer.parseInt(viewHolder.edt_idade.getText().toString()));
        p.setDtNascimento(viewHolder.edt_dt_nasc.getText().toString());

        new Insert().addPessoa(p);
        Toast.makeText(getApplicationContext(), getString(R.string.inserido_sucesso), Toast.LENGTH_SHORT).show();
        limparCampo();
    }

    private static class ViewHolder {
        private EditText edt_nome, edt_idade, edt_dt_nasc;
        private Button cadastrar, remover, alterar;

    }


    public void limparCampo() {
        viewHolder.edt_nome.setText("");
        viewHolder.edt_idade.setText("");
        viewHolder.edt_dt_nasc.setText("");
    }

    public void remover() {


        Pessoa p = new Pessoa();


        Bundle b = getIntent().getExtras();


        p.setId(b.getInt(PessoaConstantes.pessoaConstantes.PESSOA_ID));
        p.setNome(b.getString(PessoaConstantes.pessoaConstantes.PESSOA_NOME));
        p.setIdade(b.getInt(PessoaConstantes.pessoaConstantes.PESSOA_IDADE));
        p.setDtNascimento(b.getString(PessoaConstantes.pessoaConstantes.PESSOA_DT_NASC));

        new Delete().removerPessoa(p);


        Toast.makeText(getApplicationContext(), getString(R.string.deletar_sucesso), Toast.LENGTH_SHORT).show();

        startActivity(new Intent(getApplicationContext(), ReadActivity.class));

    }

    public void inserirData(int dia, int mes, int ano) {

        Calendar c = Calendar.getInstance();

        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);


        d = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                viewHolder.edt_dt_nasc.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

            }
        }, ano, dia, mes);

        d.show();


    }

    public void inserirData() {
        viewHolder.edt_dt_nasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inserirData(dia, mes, ano);
            }
        });

    }

    private void listener() {
        viewHolder.cadastrar.setOnClickListener(this);
        viewHolder.remover.setOnClickListener(this);
        viewHolder.alterar.setOnClickListener(this);
    }

    public boolean verificar() {
        if (viewHolder.edt_nome.getText().toString().trim().isEmpty()) {
            viewHolder.edt_nome.setError(getString(R.string.nome_obrigatorio));
            return false;
        } else if (viewHolder.edt_idade.getText().toString().trim().isEmpty()) {
            viewHolder.edt_idade.setError(getString(R.string.idade_obrigatorio));
            return false;
        }

        return true;
    }


    private void getBundle() {

        Bundle b = getIntent().getExtras();


        try {

            if (b.isEmpty()) {

                viewHolder.remover.setEnabled(false);


            } else {
                viewHolder.cadastrar.setEnabled(false);
                viewHolder.remover.setEnabled(true);
                viewHolder.alterar.setEnabled(true);
                String nome = b.getString(PessoaConstantes.pessoaConstantes.PESSOA_NOME);
                int idade = b.getInt(PessoaConstantes.pessoaConstantes.PESSOA_IDADE);
                String data_nasc = b.getString(PessoaConstantes.pessoaConstantes.PESSOA_DT_NASC);

                viewHolder.edt_nome.setText(nome);
                viewHolder.edt_idade.setText(String.valueOf(idade));
                viewHolder.edt_dt_nasc.setText(data_nasc);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private void alterar() {
        Pessoa p = new Pessoa();


        Bundle b = getIntent().getExtras();

        p.setId(b.getInt(PessoaConstantes.pessoaConstantes.PESSOA_ID));
        p.setNome(viewHolder.edt_nome.getText().toString());
        p.setIdade(Integer.parseInt(viewHolder.edt_idade.getText().toString()));
        p.setDtNascimento(viewHolder.edt_dt_nasc.getText().toString());
        new Update().updatePessoa(p);


        Toast.makeText(getApplicationContext(), getString(R.string.alterar_sucesso), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ReadActivity.class));


    }


}
