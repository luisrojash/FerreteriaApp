package com.example.ferreteriaapp.inventario.fragmento.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ItemListaInventarioFrBinding;
import com.example.ferreteriaapp.inventario.fragmento.adapter.holder.InventarioFrHolder;
import com.example.ferreteriaapp.inventario.fragmento.listener.InventarioListener;
import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrResponse;

import java.util.List;

public class InventarioFrAdapter extends RecyclerView.Adapter<InventarioFrHolder> {

    private List<InventarioFrResponse.ClassProductos> productoUiList;
    private InventarioListener listener;

    public InventarioFrAdapter(List<InventarioFrResponse.ClassProductos> productoUiList, InventarioListener listener) {
        this.productoUiList = productoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InventarioFrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaInventarioFrBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_lista_inventario_fr, parent, false);
        return new InventarioFrHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull InventarioFrHolder holder, int position) {
        InventarioFrResponse.ClassProductos productos = productoUiList.get(position);
        holder.bind(productos,listener);
    }

    @Override
    public int getItemCount() {
        if (productoUiList == null) return 0;
        return productoUiList.size();
    }

    public void actualizarLista(List<InventarioFrResponse.ClassProductos> listaProductos) {
        this.productoUiList.addAll(listaProductos);
        notifyDataSetChanged();
    }

    public void setMostrarLista(List<InventarioFrResponse.ClassProductos> listaProductos) {
        this.productoUiList.clear();
        this.productoUiList.addAll(listaProductos);
        notifyDataSetChanged();
    }
}
