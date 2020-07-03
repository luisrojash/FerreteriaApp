package com.example.ferreteriaapp.productos.registro;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegProductosFactory implements ViewModelProvider.Factory {
    private RegProductosRepository repository;
    private Context context;

    public RegProductosFactory(RegProductosRepository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegProductosViewModel.class)) {
            return (T) new RegProductosViewModel(repository,context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
