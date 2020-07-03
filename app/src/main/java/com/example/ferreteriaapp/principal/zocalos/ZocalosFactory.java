package com.example.ferreteriaapp.principal.zocalos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ZocalosFactory implements ViewModelProvider.Factory  {
    private ZocalosRepository repository;

    public ZocalosFactory(ZocalosRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ZocalosViewModel.class)) {
            return (T) new ZocalosViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
