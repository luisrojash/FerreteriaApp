package com.example.ferreteriaapp.inventario;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

class InventarioViewModel extends ViewModel {

    MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> mutableLiveDataListaBuscador = new MutableLiveData<>();

    private InventarioRepository repository;

    InventarioViewModel(InventarioRepository repository) {
        this.repository = repository;
    }

    void obtenerListaInventario() {
        repository.obtenerListaInventario(mutableLiveDataResponseLista);
    }

    void buscarProducto(String s) {
        repository.buscarProducto(s,mutableLiveDataListaBuscador);
    }
}
