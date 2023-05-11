package br.pro.apppetshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView lvCachorro;
    private List<Cachorro> listCachorro;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCachorro = findViewById(R.id.lvAlunos);
        btnAdicionar = findViewById( R.id.btnAdd );

        lvCachorro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                deletar( position );
                return true;
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        FormularioActivity.class);
                i.putExtra("acao", "inserir" );
                startActivity( i );
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarCachorro();
    }

    private void carregarCachorro(){
        listCachorro = CachorroDAO.getCachorro(this);

        if( listCachorro.isEmpty() ){
            lvCachorro.setEnabled(false);
            String[] listaVazia = {"Lista Vazia!"};
            adapter = new  ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
        }else {
            lvCachorro.setEnabled(true);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCachorro);
        }
        lvCachorro.setAdapter( adapter );
    }


    private void deletar(int posicao){
        Cachorro cachorro  = listCachorro.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão de " + cachorro.getNome()+"? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CachorroDAO.excluir(MainActivity.this, cachorro.getId() );
                carregarCachorro();
            }
        });
        alerta.show();
    }
}