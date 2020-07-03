package com.example.ferreteriaapp.principal.zocalos.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ferreteriaapp.databinding.ItemListaZocalosBinding;
import com.example.ferreteriaapp.principal.zocalos.listener.ZocalosListener;
import com.example.ferreteriaapp.principal.zocalos.service.ZocalosResponse;

public class ZocalosHolder extends RecyclerView.ViewHolder {
    private ItemListaZocalosBinding binding;
    private ZocalosListener listener;

    public ZocalosHolder(ItemListaZocalosBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ZocalosResponse.ClassProductos productoUi, ZocalosListener listener) {
        this.listener = listener;
        Glide.with(itemView.getContext()).load(productoUi.getProducto_image()).into(binding.imageView2);
        binding.consItemView.setOnClickListener(v -> initOnclickItem(productoUi));
    }

    private void initOnclickItem(ZocalosResponse.ClassProductos productoUi) {
        listener.onItemZocalos(productoUi);
    }
}
