package com.example.ferreteriaapp.operaciones.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.databinding.ItemOperacionesBinding;
import com.example.ferreteriaapp.operaciones.model.OperacionesUi;

public class OperacionesHolder extends RecyclerView.ViewHolder {

    private ItemOperacionesBinding binding;

    public OperacionesHolder(ItemOperacionesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(OperacionesUi operacionesUi) {
        binding.textViewNombreProducto.setText("Nombre Producto : " + operacionesUi.getNombreProducto());
        binding.textViewAlmacen.setText("Tipo: " + operacionesUi.getNombreAlmacen());
        binding.textViewCantidad.setText("Cantidad Cajas : " + operacionesUi.getCantidadEntrante());
        binding.textViewCantidadUnidad.setText("Cantidad Unidad : " + operacionesUi.getCantidadUnidadEntrante());

    }
}
