package com.example.ferreteriaapp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.SplashActivityBinding;
import com.example.ferreteriaapp.principal.PrincipalActivity;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {

    private SplashActivityBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity);
        SplashFactory factory = Injection.provideSplashViewModel(SplashActivity.this);
        viewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
        initCargarData();
        initViewModel();
    }

    private void initViewModel() {
        viewModel.hashMapCargarDataPrincipal.observe(this, this::dataCargarPrincipal);
        viewModel.mutableLiveDataMensaje.observe(this, this::responseDataPrincipal);
    }

    private void responseDataPrincipal(String mensaje) {
        mostrarMensaje(mensaje);
    }

    private void mostrarMensaje(String mensaje) {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


    private void dataCargarPrincipal(HashMap<String, Object> objectHashMap) {
        if (objectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            initStartActivity();
            return;
        }
        viewModel.responseDataPrincipal(objectHashMap);
    }

    private void initStartActivity() {
        binding.progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(SplashActivity.this, PrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void initCargarData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.onCargarDataPrincipal();
    }
}
