package com.example.ferreteriaapp.productos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ItemListaProductoGeneralBinding;
import com.example.ferreteriaapp.productos.adapter.holder.ListProductosHolder;
import com.example.ferreteriaapp.productos.listener.ListProducListener;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;

import java.util.List;

public class ListProductosAdapter extends RecyclerView.Adapter<ListProductosHolder> {

    private List<ListProductosResponse.ClassProductos> productoUiList;
    private ListProducListener listener;

    public ListProductosAdapter(List<ListProductosResponse.ClassProductos> productoUiList, ListProducListener listener) {
        this.productoUiList = productoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListProductosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaProductoGeneralBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_lista_producto_general, parent, false);
        return new ListProductosHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductosHolder holder, int position) {
        ListProductosResponse.ClassProductos productoUi = productoUiList.get(position);
        holder.bind(productoUi, listener);
    }

    @Override
    public int getItemCount() {
        if (productoUiList == null) return 0;
        return productoUiList.size();
    }

    public void actualizarLista(List<ListProductosResponse.ClassProductos> productoUiList) {
        if (productoUiList == null) return;
        this.productoUiList.clear();
        this.productoUiList.addAll(productoUiList);
        notifyDataSetChanged();
    }
}
