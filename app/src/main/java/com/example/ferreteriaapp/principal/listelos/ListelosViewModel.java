package com.example.ferreteriaapp.principal.listelos;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import timber.log.Timber;

import static com.example.ferreteriaapp.principal.listelos.ListelosFragment.ARG_CODIGO;

class ListelosViewModel extends ViewModel {

    MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista = new MutableLiveData<>();


    private ListelosRepository repository;
    private int paginaTotal;
    private int conteo = 1;
    private String tipoOperacion;
    private boolean isScrolling = true;
    private String codigoCategoria;

    ListelosViewModel(ListelosRepository repository) {
        this.repository = repository;
    }

    void obtenerBundle(Bundle arguments) {
        if (arguments.getString(ARG_CODIGO) != null) {
            tipoOperacion = "lista";
            this.codigoCategoria = arguments.getString(ARG_CODIGO);
            Timber.d("codigoCategoria : %s ", codigoCategoria);
            repository.obtenerLista(conteo, codigoCategoria, tipoOperacion, mutableLiveDataResponseLista);
        }
    }

    void paginaTotal(int pageSize) {
        this.paginaTotal = pageSize;
    }

    void cargarData(int pageIndex, int pageIndex1) {
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

    private void initLoadMorepropuesta() {
        tipoOperacion = "loadMore";
        conteo = conteo + 1;
        Timber.d("conteo : %s", conteo);
        repository.obtenerLista(conteo, codigoCategoria, tipoOperacion, mutableLiveDataResponseLista);
    }
}
