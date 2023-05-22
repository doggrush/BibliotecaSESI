package com.example.biblioteca;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TelaCadastro extends AppCompatActivity {
    static ArrayList listaLivros;
    EditText nome, genero, descricao, paginas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().hide();
        nome = findViewById(R.id.novoNome);
        genero = findViewById(R.id.novoGen);
        descricao = findViewById(R.id.novoDesc);
        paginas = findViewById(R.id.novoPag);
    }
    public void cadastra(View v){
        if(validaCampos()){
            efetivaCadastro();
        }
        else{
            Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show();
        }
    }
    public void efetivaCadastro(){
        String nm = nome.getText().toString();
        String gen = genero.getText().toString();
        String desc = descricao.getText().toString();
        String pag = (paginas.getText().toString()+" Páginas");
        Livro L = new Livro(nm, gen, pag, desc);
        L.salvar();
        super.onBackPressed();
    }
    public boolean validaCampos(){
        if(nome.getText().toString().isEmpty()||genero.getText().toString().isEmpty()||descricao.getText().toString().isEmpty()||paginas.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}