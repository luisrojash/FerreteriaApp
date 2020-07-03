package com.example.ferreteriaapp.productos.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ferreteriaapp.databinding.ItemListaProductoGeneralBinding;
import com.example.ferreteriaapp.productos.listener.ListProducListener;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;

public class ListProductosHolder extends RecyclerView.ViewHolder {
    private final ItemListaProductoGeneralBinding binding;
    ListProducListener listener;

    public ListProductosHolder(ItemListaProductoGeneralBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(ListProductosResponse.ClassProductos productoUi,
                     ListProducListener listener) {
        this.listener = listener;
        binding.textViewNombreProducto.setText("Pro.Nombre: " + productoUi.getProducto_nombre());
        binding.textViewCodigoProducto.setText("Prod.Codigo: " + productoUi.getProducto_cod_producto());
        binding.textViewNombreProveedor.setText("Prove.Nombre : " + productoUi.getProveedor_nombre());
        Glide.with(itemView.getContext()).load(productoUi.getProducto_image()).into(binding.imageView2);
        binding.imageEditar.setOnClickListener(v -> initStartImageEditar(productoUi));
        binding.imageDelete.setOnClickListener(v -> initStartImageEliminar(productoUi));
    }

    private void initStartImageEliminar(ListProductosResponse.ClassProductos productoUi) {
        listener.onClickEliminarProducto(productoUi);
    }

    private void initStartImageEditar(ListProductosResponse.ClassProductos productoUi) {
        listener.onClickEditarProducto(productoUi);
    }
}
