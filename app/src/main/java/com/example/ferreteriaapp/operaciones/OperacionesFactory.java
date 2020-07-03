package com.example.ferreteriaapp.operaciones;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OperacionesFactory implements ViewModelProvider.Factory {

    private OperacionesRepository repository;

    public OperacionesFactory(OperacionesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OperacionesViewModel.class)) {
            return (T) new OperacionesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
