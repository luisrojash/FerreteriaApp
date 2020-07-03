package com.example.ferreteriaapp.principal.zocalos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ItemListaZocalosBinding;
import com.example.ferreteriaapp.principal.zocalos.adapter.holder.ZocalosHolder;
import com.example.ferreteriaapp.principal.zocalos.listener.ZocalosListener;
import com.example.ferreteriaapp.principal.zocalos.service.ZocalosResponse;

import java.util.List;

public class ZocalosAdapter extends RecyclerView.Adapter<ZocalosHolder> {

    private List<ZocalosResponse.ClassProductos> classProductosList;
    private ZocalosListener listener;

    public ZocalosAdapter(List<ZocalosResponse.ClassProductos> classProductosList, ZocalosListener listener) {
        this.classProductosList = classProductosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ZocalosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaZocalosBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_lista_zocalos, parent, false);
        return new ZocalosHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ZocalosHolder holder, int position) {
        ZocalosResponse.ClassProductos productoUi = classProductosList.get(position);
        holder.bind(productoUi, listener);
    }

    @Override
    public int getItemCount() {
        if (classProductosList == null) return 0;
        return classProductosList.size();
    }

    public void actualizarLista(List<ZocalosResponse.ClassProductos> classProductosList) {
        this.classProductosList.addAll(classProductosList);
        notifyDataSetChanged();
    }

    public void setMostrarLista(List<ZocalosResponse.ClassProductos> classProductosList) {
        this.classProductosList.clear();
        this.classProductosList.addAll(classProductosList);
        notifyDataSetChanged();
    }
}
