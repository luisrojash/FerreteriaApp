package com.example.ferreteriaapp.operaciones;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.buscador.BuscarProductosAdapter;
import com.example.ferreteriaapp.buscador.modelo.BuscarProductosUi;
import com.example.ferreteriaapp.databinding.ActivityEntradaBinding;
import com.example.ferreteriaapp.lote.LoteAdapter;
import com.example.ferreteriaapp.lote.modelo.LoteUi;
import com.example.ferreteriaapp.operaciones.adapter.OperacionesAdapter;
import com.example.ferreteriaapp.operaciones.adapter.SpinnerDefault;
import com.example.ferreteriaapp.operaciones.model.ModelDefault;
import com.example.ferreteriaapp.operaciones.model.OperacionesUi;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class OperacionesActivity extends AppCompatActivity {
    private ActivityEntradaBinding binding;
    private OperacionesViewModel viewModel;
    private BuscarProductosAdapter buscarProductosAdapter;
    private LoteAdapter buscarLoteAdapter;
    private List<ModelDefault> listaAlmacen;
    private OperacionesAdapter operacionesAdapter;
    private String tipoOperacion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_entrada);
        OperacionesFactory factory = Injection.provideEntrada(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(OperacionesViewModel.class);
        tipoOperacion = getIntent().getStringExtra("tipoOperacion");
        validacionTipoOperacion(tipoOperacion);
        binding.textView.setText(tipoOperacion + " Productos");
        Timber.d("OperacionesActivity : %s ", tipoOperacion);
        getSupportActionBar().setTitle(tipoOperacion + " Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.bundle(tipoOperacion);
        initView();
        initViewModel();
        initViewOnclickListener();
    }

    private void validacionTipoOperacion(String tipoOperacion) {
        switch (tipoOperacion) {
            case "Entrada":
                binding.buttonNuevo.setVisibility(View.VISIBLE);
                binding.buttonEditar.setVisibility(View.VISIBLE);
                break;
            default:
                binding.textViewCant.setText("Escriba Cajas Salientes");
                binding.textViewCantidadUnidad.setText("Unidades Salientes");
                binding.buttonAgregar.setText("Terminar Salida");
                break;
        }
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

    private String tipoStatus = null;

    private void initViewOnclickListener() {
        binding.spinnerAlmacen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ModelDefault almacen = (ModelDefault) binding.spinnerAlmacen.getSelectedItem();
                viewModel.almacenSelected(almacen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.buttonNuevo.setOnClickListener(v -> {
            tipoStatus = "buttonNuevo";
            binding.buttonEditar.setBackgroundColor(getResources().getColor(R.color.md_blue_grey_200));
            binding.buttonNuevo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            viewModel.initTipoEstado(tipoStatus);

        });
        binding.buttonEditar.setOnClickListener(v -> {
            tipoStatus = "buttonEditar";
            binding.buttonEditar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            binding.buttonNuevo.setBackgroundColor(getResources().getColor(R.color.md_blue_grey_200));
            viewModel.initTipoEstado(tipoStatus);
        });
    }

    private void initViewModel() {
        viewModel.obtenerListaAlmacen();
        viewModel.responseMutableLiveData.observe(this, this::mostrarListaBuscar);
        viewModel.responseView.observe(this, this::mostrarView);
        viewModel.responsePosicion.observe(this, this::obtenerPosicion);
        viewModel.responseFabAgregar.observe(this, this::responseFab);
        viewModel.responseLote.observe(this, this::responseListaLote);
        viewModel.responseProductoUi.observe(this, this::resposeProductoUi);
    }

    private void resposeProductoUi(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get("buscarProductosUi") != null) {
            BuscarProductosUi buscarProductosUi = (BuscarProductosUi) stringObjectHashMap.get("buscarProductosUi");
            Timber.d("buscarProductosUi : %s ", buscarProductosUi.getMedidadDescripcion());
            binding.textViewMedidaDescripcion.setText(buscarProductosUi.getMedidadDescripcion());
        }
    }

    private void responseListaLote(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get("listaLote") != null) {
            List<LoteUi> loteUiList = (List<LoteUi>) stringObjectHashMap.get("listaLote");
            buscarLoteAdapter.actualizarLista(loteUiList);
        }

        binding.editTextLote.setAdapter(buscarLoteAdapter);
        binding.editTextLote.setOnItemClickListener((parent, view, position, id) -> {
            try {
                LoteUi loteUi = (LoteUi) parent.getAdapter().getItem(position);
                binding.textViewStockProducto.setText(loteUi.getStockProducto());
                binding.textViewStockUnidad.setText(loteUi.getUnidadProducto());
                viewModel.loteSelected(loteUi);
                Timber.d("buscarProductosUi : %s ", buscarProductosUi.getNombreProductos());
            } catch (Exception e) {
                Timber.d("Exception ");
            }
        });
    }

    private void responseFab(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            String mensaje = (String) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
            viewModel.limpiarData();
            buscarLoteAdapter.limpiardata();
            operacionesAdapter.limpiarData();
            limpiarData();
            mostrarMensaje(mensaje);
            finish();
        }
    }

    private void limpiarData() {
        binding.autoCompleteTextviewProducto.setText(null);
        binding.autoCompleteTextviewProducto.setText(null);
        binding.editTextLote.setText(null);
        binding.editextCantidad.setText(null);
        binding.textViewStockProducto.setText("0");
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void obtenerPosicion(Integer integer) {
        binding.spinnerAlmacen.setSelection(integer);
    }

    private void mostrarView(HashMap<String, Object> stringObjectHashMap) {
        mostrarOperaciones(stringObjectHashMap);
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            List<ModelDefault> listaAlmacen = (List<ModelDefault>) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
            initListAlmacen(listaAlmacen);
        }
    }


    private void mostrarOperaciones(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get("operaciones") != null) {
            OperacionesUi operacionesUi = (OperacionesUi) stringObjectHashMap.get("operaciones");
            operacionesAdapter.agregarOperaciones(operacionesUi);
        }
    }

    private void initListAlmacen(List<ModelDefault> obtenerAlmacenLista) {
        viewModel.listaAlmacen(obtenerAlmacenLista);
        SpinnerDefault spinnerDefault = new SpinnerDefault(this,
                R.layout.custom_spinner_default, obtenerAlmacenLista);
        binding.spinnerAlmacen.setAdapter(spinnerDefault);
    }


    BuscarProductosUi buscarProductosUi;

    private void mostrarListaBuscar(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            List<BuscarProductosUi> listaProductos = (List<BuscarProductosUi>) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
            buscarProductosAdapter.actualizarLista(listaProductos);
        }
        binding.autoCompleteTextviewProducto.setAdapter(buscarProductosAdapter);
        binding.autoCompleteTextviewProducto.setOnItemClickListener((parent, view, position, id) -> {
            try {
                buscarProductosUi = (BuscarProductosUi) parent.getAdapter().getItem(position);
                viewModel.productoSelected(buscarProductosUi);
                Timber.d("buscarProductosUi : %s ", buscarProductosUi.getNombreProductos());
            } catch (Exception e) {
                Timber.d("Exception ");
            }
        });
    }

    private void initView() {
        binding.fabAgregarItem.setOnClickListener(v -> {
            String lote = binding.editTextLote.getText().toString();
            String cantidad = binding.editextCantidad.getText().toString();
            String cantidadUnidad = binding.editextProductoUnidad.getText().toString();
            viewModel.fabAgregarItem(lote, cantidad, cantidadUnidad);
          /*  switch (tipoOperacion) {
                case "Entrada":
                    String lote = binding.editTextLote.getText().toString();
                    String cantidad = binding.editextCantidad.getText().toString();
                    viewModel.fabAgregarItem(lote, cantidad);
                    break;
                case "Salida":
                    Timber.d("Eliga Lote Existente");
                    break;
                default:
                    break;
            }*/

        });
        binding.buttonAgregar.setOnClickListener(v -> viewModel.enviarOperacion());

        buscarProductosAdapter = new BuscarProductosAdapter(this, new ArrayList<>());
        operacionesAdapter = new OperacionesAdapter(new ArrayList<>());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(operacionesAdapter);
        binding.autoCompleteTextviewProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                initAdapterBuscar(editable.toString());
            }
        });

        buscarLoteAdapter = new LoteAdapter(this, new ArrayList<>());

        binding.editTextLote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                initAdapterBuscarLote(editable.toString());
            }
        });
    }

    private void initAdapterBuscarLote(String toString) {
        viewModel.buscarLote(toString);
    }

    private void initAdapterBuscar(String toString) {
        viewModel.buscarProductos(toString);
    }
}
