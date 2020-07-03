package com.example.ferreteriaapp.principal.listelos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListelosFactory implements ViewModelProvider.Factory  {
    private ListelosRepository repository;

    public ListelosFactory(ListelosRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListelosViewModel.class)) {
            return (T) new ListelosViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
