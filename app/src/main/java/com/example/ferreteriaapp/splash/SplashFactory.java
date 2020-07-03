package com.example.ferreteriaapp.splash;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SplashFactory implements ViewModelProvider.Factory  {

    private SplashRepository repository;

    public SplashFactory(SplashRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
