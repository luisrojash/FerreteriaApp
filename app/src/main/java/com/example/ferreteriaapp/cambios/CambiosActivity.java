package com.example.ferreteriaapp.cambios;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ActivityCambiosBinding;

public class CambiosActivity extends AppCompatActivity {
    private ActivityCambiosBinding binding;
    private String tipoOperacion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cambios);
        /*OperacionesFactory factory = Injection.provideEntrada(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(OperacionesViewModel.class);*/
        tipoOperacion = getIntent().getStringExtra("tipoOperacion");
        //binding.textView.setText(tipoOperacion + " Productos");
        //Timber.d("OperacionesActivity : %s ", tipoOperacion);
        getSupportActionBar().setTitle(tipoOperacion + " Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
