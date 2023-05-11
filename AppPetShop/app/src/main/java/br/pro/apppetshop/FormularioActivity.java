package br.pro.apppetshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etIdade;
    private Button btnSalvar;

    private Cachorro cachorro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        btnSalvar = findViewById(R.id.btn_salvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar(){

        String nome = etNome.getText().toString();
        if ( nome.isEmpty() ){
            Toast.makeText(this,
                    "O campo nome deve ser preenchido!" ,
                    Toast.LENGTH_LONG
            ).show();
        }else {
           cachorro = new Cachorro();
            cachorro.setNome( nome );
            cachorro.setIdade( etIdade.getText().toString() );
            CachorroDAO.inserir(this, cachorro);
            etNome.setText( "" );
            etIdade.setText( "" );
            cachorro = null;
        }
    }
}