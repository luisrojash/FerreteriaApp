package com.example.ferreteriaapp.principal.fragment.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.principal.fragment.model.Productos;
import com.example.ferreteriaapp.principal.fragment.service.ProductosResponse;
import com.example.ferreteriaapp.principal.listener.FragmentListener;

public class ProductosHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView imgProduct;
    private AppCompatTextView txtTitle;
    private AppCompatTextView txtDesc;
    private ConstraintLayout root;


    public ProductosHolder(@NonNull View itemView) {
        super(itemView);
        imgProduct = itemView.findViewById(R.id.img_product);
        txtTitle = itemView.findViewById(R.id.txt_title);
        txtDesc = itemView.findViewById(R.id.txt_desc);
        root = itemView.findViewById(R.id.root);
    }

    public void bind(ProductosResponse.ClassProductos productos, FragmentListener listener) {
        Glide
                .with(itemView.getContext())
                .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .load(productos.getProducto_image())
                .into(imgProduct);
        txtTitle.setText("Stock: " + productos.getProducto_Stock());
        txtDesc.setText(productos.getProducto_nombre());
        root.setOnClickListener(v -> {
             if (listener != null) listener.onItemClickFragment(productos);
        });

    }
}
