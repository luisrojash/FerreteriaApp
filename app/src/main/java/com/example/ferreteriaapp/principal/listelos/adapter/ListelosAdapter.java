package com.example.ferreteriaapp.principal.listelos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ItemListaListelosBinding;
import com.example.ferreteriaapp.principal.listelos.adapter.holder.ListelosHolder;
import com.example.ferreteriaapp.principal.listelos.listener.ListelosListener;
import com.example.ferreteriaapp.principal.listelos.service.ListelosResponse;

import java.util.List;

public class ListelosAdapter extends RecyclerView.Adapter<ListelosHolder> {
    private List<ListelosResponse.ClassProductos> classProductosList;
    private ListelosListener listener;

    public ListelosAdapter(List<ListelosResponse.ClassProductos> classProductosList, ListelosListener listener) {
        this.classProductosList = classProductosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListelosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaListelosBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_lista_listelos, parent, false);
        return new ListelosHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListelosHolder holder, int position) {
        ListelosResponse.ClassProductos productoUi = classProductosList.get(position);
        holder.bind(productoUi, listener);
    }

    @Override
    public int getItemCount() {
        if (classProductosList == null) return 0;
        return classProductosList.size();
    }

    public void actualizarLista(List<ListelosResponse.ClassProductos> classProductosList) {
        this.classProductosList.addAll(classProductosList);
        notifyDataSetChanged();
    }

    public void setMostrarLista(List<ListelosResponse.ClassProductos> listaProductos) {
        this.classProductosList.clear();
        this.classProductosList.addAll(listaProductos);
        notifyDataSetChanged();
    }
}
