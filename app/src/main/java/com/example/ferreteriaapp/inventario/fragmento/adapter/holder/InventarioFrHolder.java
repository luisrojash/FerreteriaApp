package com.example.ferreteriaapp.inventario.fragmento.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ferreteriaapp.databinding.ItemListaInventarioFrBinding;
import com.example.ferreteriaapp.inventario.fragmento.listener.InventarioListener;
import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrResponse;

public class InventarioFrHolder extends RecyclerView.ViewHolder {

    private ItemListaInventarioFrBinding binding;

    public InventarioFrHolder(ItemListaInventarioFrBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(InventarioFrResponse.ClassProductos productos, InventarioListener listener) {
        Glide
                .with(itemView.getContext())
                .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .load(productos.getProducto_image())
                .into(binding.imgProduct);
        binding.txtTitle.setText("Stock: " + productos.getProducto_Stock());
        binding.txtDesc.setText(productos.getProducto_nombre());

        itemView.setOnClickListener(v -> listener.itemClickInventario(productos));
    }
}
