package com.example.biblioteca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {
    Context context;
    ArrayList<Livro> lista;
    Adaptador.OnItemClickListener listener;

    public Adaptador(Context context, ArrayList<Livro> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }
    @NonNull
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new Adaptador.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyViewHolder holder, int position) {
        Livro L = lista.get(position);
        holder.nome.setText(L.getNome());
        holder.genero.setText(L.getGenero());
        holder.descricao.setText(L.getDescricao());
        holder.paginas.setText(L.getPaginas());
        holder.itemView.setOnClickListener(view ->{
            listener.onItemClick(L);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Livro L);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, genero, descricao, paginas;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            genero = itemView.findViewById(R.id.genero);
            descricao = itemView.findViewById(R.id.descricao);
            paginas = itemView.findViewById(R.id.paginas);
        }
    }
}
