package com.example.ferreteriaapp.principal.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import timber.log.Timber;

import static com.example.ferreteriaapp.principal.zocalos.ZocalosFragment.ARG_CODIGO;

class ProductosViewModel extends ViewModel {

    MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista = new MutableLiveData<>();


    private ProductosRepository  repository;
    private int paginaTotal;
    private int conteo = 1;
    private boolean isScrolling = true;
    private String codigoCategoria;
    private String tipoOperacion;

    ProductosViewModel(ProductosRepository repository) {
        this.repository = repository;
    }

    void obtenerBundle(Bundle arguments) {
        if (arguments.getString(ARG_CODIGO) != null) {
            this.codigoCategoria = arguments.getString(ARG_CODIGO);
            Timber.d("codigoCategoria : %s ", codigoCategoria);
            tipoOperacion = "lista";
            repository.obtenerLista(conteo, codigoCategoria, tipoOperacion, mutableLiveDataResponseLista);
        }
    }

    void paginaTotal(int pageSize) {
        this.paginaTotal = pageSize;
    }

    private void agregarItemsLista() {

        Handler handler = new Handler();
        handler.postDelayed(this::initLoadMorepropuesta, 2000);
    }

    private void initLoadMorepropuesta() {
        tipoOperacion = "loadMore";
        conteo = conteo + 1;
        Timber.d("conteo : %s", conteo);
        repository.obtenerLista(conteo, codigoCategoria, tipoOperacion, mutableLiveDataResponseLista);
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
}
