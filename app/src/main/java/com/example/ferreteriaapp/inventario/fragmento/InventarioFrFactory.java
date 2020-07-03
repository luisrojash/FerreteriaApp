package com.example.ferreteriaapp.inventario.fragmento;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InventarioFrFactory implements ViewModelProvider.Factory {

    private InventarioFrRepository repository;

    public InventarioFrFactory(InventarioFrRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InventarioFrViewModel.class)) {
            return (T) new InventarioFrViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
