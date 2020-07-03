package com.example.ferreteriaapp.inventario.fragmento;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import timber.log.Timber;

class InventarioFrViewModel extends ViewModel {

    MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista = new MutableLiveData<>();

    private InventarioFrRepository repository;
    private int paginaTotal;
    private int conteo = 1;
    private boolean isScrolling = true;
    private String almacenId;
    private String tipoOperacion;

    InventarioFrViewModel(InventarioFrRepository repository) {
        this.repository = repository;
    }

    void obtenerListaAlmacen(String almacenId) {
        this.almacenId = almacenId;
        tipoOperacion = "lista";
        repository.obtenerListaAlmacen(almacenId, conteo, tipoOperacion, mutableLiveDataResponseLista);
    }

    void paginaTotal(int pageSize) {
        this.paginaTotal = pageSize;
    }

    private void initLoadMorepropuesta() {
        tipoOperacion = "loadMore";
        conteo = conteo + 1;
        Timber.d("conteo : %s", conteo);
        repository.obtenerListaAlmacen(almacenId, conteo, tipoOperacion, mutableLiveDataResponseLista);
    }

    void cargarData(int pageIndex, int pageIndex1) {
        Timber.d("pageIndex : %s ", pageIndex);
        if (paginaTotal == (pageIndex + 1)) {
            if (isScrolling) {
                isScrolling = false;
                agregarItemsLista();
            }
            Timber.d("cargarData");
        }
    }

    private void agregarItemsLista() {
        Handler handler = new Handler();
        handler.postDelayed(this::initLoadMorepropuesta, 2000);
    }
}
