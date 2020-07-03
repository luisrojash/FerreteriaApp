package com.example.ferreteriaapp.detalles;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.DetallesDialogBinding;

public class DetallesFragment extends DialogFragment {

   // private DetallesViewModel viewModel;
    private DetallesDialogBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detalles_dialog, container, false);

        initView();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();


        if (getDialog() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    private void initView() {

        String producto_image = getArguments().getString("producto_image");
        String producto_nombre = getArguments().getString("producto_nombre");
        String producto_descripcion = getArguments().getString("producto_descripcion");
        String producto_minimo = getArguments().getString("producto_minimo");
        String producto_precio_in = getArguments().getString("producto_precio_in");
        String producto_precio_out = getArguments().getString("producto_precio_out");
        String producto_cod_producto = getArguments().getString("producto_cod_producto");
        String producto_Stock = getArguments().getString("producto_Stock");
        String color_nombre = getArguments().getString("color_nombre");
        String categoria_abrev = getArguments().getString("categoria_abrev");
        String tipologia_nombre = getArguments().getString("tipologia_nombre");
        String material_nombre = getArguments().getString("material_nombre");
        String superficie_nombre = getArguments().getString("superficie_nombre");
        String producto_Lote = getArguments().getString("producto_Lote");
        String almacen_nombre = getArguments().getString("almacen_nombre");
        Glide.with(requireContext()).load(producto_image).into(binding.imageViewProducto);
        binding.textViewNombreProducto.setText(producto_nombre);
        binding.textViewDescripcionProducto.setText(producto_descripcion);
        binding.textViewInventarioMinimo.setText("Inventario Minimo: " + producto_minimo);
        binding.textViewPrecioEntrada.setText("Precio Entrada : " + producto_precio_in);
        binding.textViewPrecioSalida.setText("Precio Salida : " + producto_precio_out);
        binding.textViewProductoCodigo.setText("Codigo Producto: " + producto_cod_producto);
        binding.textViewStockInicial.setText("Stock Producto : " + producto_Stock);
        binding.textViewProveedor.setText("");
        binding.textViewColor.setText("Color Nombre: " + color_nombre);
        binding.textViewCategoria.setText("Categoria Nombre: " + categoria_abrev);
        binding.textViewTipologia.setText("Tipologia Nombre: " + tipologia_nombre);
        binding.textViewMaterial.setText("Material Nombre: " + material_nombre);
        binding.textViewSuperficie.setText("Superficie Nombre: " + superficie_nombre);
        binding.textViewLote.setText("Lote Nombre : " + producto_Lote);
        binding.textViewAlmacen.setText("Almacen Nombre: "+almacen_nombre);

    }
}
