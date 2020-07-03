package com.example.ferreteriaapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.LoginActivityBinding;
import com.example.ferreteriaapp.principal.PrincipalActivity;
import com.example.ferreteriaapp.util.AppConstants;
import com.example.ferreteriaapp.util.SessionManager;

import java.util.HashMap;

import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {
    private LoginActivityBinding binding;
    private LoginViewModel viewModel;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        LoginFactory factory = Injection.provideLoginViewModel();
        session = new SessionManager(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        initViewModel();

    }


    private void initViewModel() {
        binding.btnLogin.setOnClickListener(v -> initValidarUsuario());
        viewModel.mutableLiveDataMensaje.observe(this, this::mostrarMensaje);
        viewModel.mutableLiveDataInitLogin.observe(this, this::validateResponseLogin);
    }

    private void mostrarMensaje(String s) {
        binding.btnLogin.setEnabled(true);
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void validateResponseLogin(HashMap<String, Object> objectHashMap) {
        if (objectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            Timber.d("initStartMainACtivity");
            initMainActivity();
            session.createLoginSession("Sesion Creada", "luisrojas@upeu.edu.pe");
            binding.progressBar.setVisibility(View.GONE);
            binding.btnLogin.setEnabled(true);
        }
    }

    private void initMainActivity() {
        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void initValidarUsuario() {
        binding.btnLogin.setEnabled(false);
        String usuario = String.valueOf(binding.etUsuario.getText());
        String clave = String.valueOf(binding.etClave.getText());
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.initLogin(usuario, clave);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityCompat.finishAffinity(LoginActivity.this);
    }
}
