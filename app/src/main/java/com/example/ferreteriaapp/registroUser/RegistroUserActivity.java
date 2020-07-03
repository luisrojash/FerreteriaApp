package com.example.ferreteriaapp.registroUser;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.RegistroUserActivityBinding;

public class RegistroUserActivity extends AppCompatActivity {

    private RegistroUserViewModel viewModel;
    private RegistroUserActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.registro_user_activity);
        RegistroUserFactory factory = Injection.provideRegistroUserViewModel();
        viewModel = new ViewModelProvider(this, factory).get(RegistroUserViewModel.class);
        initView();
    }

    private void initView() {
        binding.btnRegistro.setOnClickListener(v->registrarUser());
    }

    private void registrarUser() {
        String usuario = String.valueOf(binding.etUsuario.getText());
        String clave = String.valueOf(binding.etClave.getText());
        viewModel.registrarUsuario(usuario,clave);
    }
}
