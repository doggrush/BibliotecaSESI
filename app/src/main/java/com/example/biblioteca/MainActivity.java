package com.example.biblioteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Livro> listaLivros = new ArrayList<>();
    RecyclerView recycler;
    Adaptador adaptador;
    EditText pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        pesquisa = findViewById(R.id.pesquisa);
        recycler = findViewById(R.id.rv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new Adaptador(this, listaLivros, new Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Livro L) {
                Toast.makeText(MainActivity.this, L.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }

    public void telaCadastro(View v) {
        Intent i = new Intent(this, TelaCadastro.class);
        startActivity(i);
        TelaCadastro.listaLivros = listaLivros;
    }

    public void pesquisa(View v) {
        String text = pesquisa.getText().toString().toLowerCase();
        ArrayList<Livro> FiltroLivro = new ArrayList<>();
        for (Livro L : listaLivros) {
            if (L.getNome().toLowerCase().contains(text)) {
                FiltroLivro.add(L);
            }
        }
        adaptador = new Adaptador(this, FiltroLivro, new Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Livro L) {
                Toast.makeText(MainActivity.this, L.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carrega();
    }
    public void carrega() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Livros").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaLivros.clear();
                for (DataSnapshot dataSnap : snapshot.getChildren()) {
                    Livro L = (Livro) dataSnap.getValue(Livro.class);
                    listaLivros.add(L);
                }
                Adaptador adapter = new Adaptador(MainActivity.this, listaLivros, new Adaptador.OnItemClickListener() {
                    @Override
                    public void onItemClick(Livro L) {
                        Toast.makeText(MainActivity.this, L.getNome(), Toast.LENGTH_SHORT).show();
                    }
                });
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}