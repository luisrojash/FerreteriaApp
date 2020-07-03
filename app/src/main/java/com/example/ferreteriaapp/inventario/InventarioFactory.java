package com.example.ferreteriaapp.inventario;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InventarioFactory implements ViewModelProvider.Factory {
    private InventarioRepository repository;

    public InventarioFactory(InventarioRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InventarioViewModel.class)) {
            return (T) new InventarioViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
