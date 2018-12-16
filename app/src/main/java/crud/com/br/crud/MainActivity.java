package crud.com.br.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import crud.com.br.crud.activity.CreateActivity;
import crud.com.br.crud.activity.ReadActivity;
import crud.com.br.crud.crud.Create;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnCadastrar).setOnClickListener(this);
        findViewById(R.id.btnConsultar).setOnClickListener(this);


        new Create().createTable();


    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btnCadastrar) {

            getActivity(CreateActivity.class);
        } else if (id == R.id.btnConsultar) {
            getActivity(ReadActivity.class);
        }
    }

    public void getActivity(Class c) {

        Intent i = new Intent(getApplicationContext(), c);

        startActivity(i);

    }






}
