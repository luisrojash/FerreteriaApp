package com.example.ferreteriaapp.productos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ActivityListProductosBinding;
import com.example.ferreteriaapp.productos.adapter.ListProductosAdapter;
import com.example.ferreteriaapp.productos.listener.ListProducListener;
import com.example.ferreteriaapp.productos.registro.RegProductosActivity;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;
import com.example.ferreteriaapp.util.AppConstants;
import com.gcssloop.widget.PagerConfig;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

public class ListProductosActivity extends AppCompatActivity implements
        PagerGridLayoutManager.PageListener, ListProducListener {
    private ActivityListProductosBinding binding;
    private ListProductosViewModel viewModel;
    private ListProductosAdapter productosAdapter;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_productos);
        ListProductosFactory factory = Injection.providelistProductoslViewModel();
        viewModel = new ViewModelProvider(this, factory).get(ListProductosViewModel.class);
        dialog = new Dialog(ListProductosActivity.this);
        initPagerGrid();
        initViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.obtenerListaProductos();
    }

    private void initViewModel() {
        getSupportActionBar().setTitle("Lista Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.mutableLiveDataResponseLista.observe(this, this::mostrarLista);
        viewModel.mutableViewVista.observe(this, this::mostrarVistas);
        binding.fab.setOnClickListener(v -> startRegistro());
    }

    private void mostrarVistas(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            viewModel.obtenerListaProductos();
            dialog.dismiss();
        }
    }

    private void startRegistro() {
        Intent intent = new Intent(ListProductosActivity.this, RegProductosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void mostrarLista(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            ListProductosResponse listProductosResponseList = (ListProductosResponse) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
            productosAdapter.actualizarLista(listProductosResponseList.getListaProductos());
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void initPagerGrid() {
        PagerGridLayoutManager mLayoutManager = new PagerGridLayoutManager(2, 2, PagerGridLayoutManager.HORIZONTAL);
        mLayoutManager.setPageListener(this);
        binding.reciclador.setLayoutManager(mLayoutManager);
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(binding.reciclador);
        PagerConfig.setShowLog(true);
        productosAdapter = new ListProductosAdapter(new ArrayList<>(), this);
        binding.reciclador.setAdapter(productosAdapter);
    }

    @Override
    public void onPageSizeChanged(int pageSize) {

    }

    @Override
    public void onPageSelect(int pageIndex, int pageLastIndex) {

    }

    @Override
    public void onClickEliminarProducto(ListProductosResponse.ClassProductos productoUi) {
        Timber.d("onClickEliminarProducto");
        dialog.setContentView(R.layout.custom);
        Button buttonSi = dialog.findViewById(R.id.buttonSi);
        Button buttonNo = dialog.findViewById(R.id.buttonNo);
        buttonSi.setOnClickListener(v -> viewModel.onClickEliminarProducto(productoUi));
        buttonNo.setOnClickListener(v -> dialog.dismiss());
        dialog.show();

    }

    @Override
    public void onClickEditarProducto(ListProductosResponse.ClassProductos productoUi) {
        Intent i = new Intent(ListProductosActivity.this, RegProductosActivity.class);
        i.putExtra("productoUi", productoUi);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Timber.d("nav_home");
                break;
            default:
                finish();
                break;
        }
        return true;
    }

}
