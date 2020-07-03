package com.example.ferreteriaapp.principal.listelos.listener;

import com.example.ferreteriaapp.principal.listelos.service.ListelosResponse;

public interface ListelosListener {
    void onItemListelos(ListelosResponse.ClassProductos classProductos);
}
