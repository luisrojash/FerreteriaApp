package com.example.ferreteriaapp.productos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListProductosFactory implements ViewModelProvider.Factory {
    private ListProductosRepository repository;

    public ListProductosFactory(ListProductosRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListProductosViewModel.class)) {
            return (T) new ListProductosViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
