package com.example.ferreteriaapp.principal.listelos.adapter.holder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ferreteriaapp.databinding.ItemListaListelosBinding;
import com.example.ferreteriaapp.principal.listelos.listener.ListelosListener;
import com.example.ferreteriaapp.principal.listelos.service.ListelosResponse;

public class ListelosHolder extends RecyclerView.ViewHolder {
    private ItemListaListelosBinding binding;
    private ListelosListener listener;

    public ListelosHolder(ItemListaListelosBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ListelosResponse.ClassProductos productoUi, ListelosListener listener) {
        this.listener = listener;
        Glide.with(itemView.getContext()).load(productoUi.getProducto_image()).into(binding.imageView2);
        binding.consItemView.setOnClickListener(v -> initOnclickItem(productoUi));
    }

    private void initOnclickItem(ListelosResponse.ClassProductos productoUi) {
        listener.onItemListelos(productoUi);
    }
}
