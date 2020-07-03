package com.example.ferreteriaapp.productos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ferreteriaapp.productos.service.ListProductosResponse;

import java.util.HashMap;

class ListProductosViewModel extends ViewModel {
    private ListProductosRepository repository;
    MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> mutableViewVista = new MutableLiveData<>();

    ListProductosViewModel(ListProductosRepository repository) {
        this.repository = repository;
    }

    void obtenerListaProductos() {
        int pageCount = 0;
        repository.obtenerListaProdutos(pageCount, mutableLiveDataResponseLista);
    }


    void onClickEliminarProducto(ListProductosResponse.ClassProductos productoUi) {
        repository.onClickEliminarProducto(productoUi,mutableViewVista);
    }
}
