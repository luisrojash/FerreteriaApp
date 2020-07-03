package com.example.ferreteriaapp.principal.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.principal.fragment.adapter.holder.ProductosHolder;
import com.example.ferreteriaapp.principal.fragment.model.Productos;
import com.example.ferreteriaapp.principal.fragment.service.ProductosResponse;
import com.example.ferreteriaapp.principal.listener.FragmentListener;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosHolder> {

    private List<ProductosResponse.ClassProductos> productosList;
    private FragmentListener listener;

    public ProductosAdapter(List<ProductosResponse.ClassProductos> productosList, FragmentListener listener) {
        this.productosList = productosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        return new ProductosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosHolder holder, int position) {
        ProductosResponse.ClassProductos productos = productosList.get(position);
        holder.bind(productos,listener);
    }

    @Override
    public int getItemCount() {
        if (productosList == null) return 0;
        return productosList.size();
    }

    public void setMostrarLista(List<ProductosResponse.ClassProductos> classProductosList) {
        this.productosList.clear();
        this.productosList.addAll(classProductosList);
        notifyDataSetChanged();
    }

    public void actualizarLista(List<ProductosResponse.ClassProductos> classProductosList) {
        this.productosList.addAll(classProductosList);
        notifyDataSetChanged();
    }
}

