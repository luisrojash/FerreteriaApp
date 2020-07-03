package com.example.ferreteriaapp.principal.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductosFactory implements ViewModelProvider.Factory{
    private ProductosRepository repository;

    public ProductosFactory(ProductosRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductosViewModel.class)) {
            return (T) new ProductosViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
