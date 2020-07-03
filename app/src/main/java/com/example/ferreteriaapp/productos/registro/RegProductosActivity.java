package com.example.ferreteriaapp.productos.registro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ActivityRegProductosBinding;
import com.example.ferreteriaapp.productos.registro.adapter.ArrayAdapterDefault;
import com.example.ferreteriaapp.productos.registro.adapter.SpinnerDefault;
import com.example.ferreteriaapp.productos.registro.ui.ModelDefault;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;
import com.example.ferreteriaapp.util.AppConstants;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class RegProductosActivity extends AppCompatActivity {
    private ActivityRegProductosBinding binding;
    private RegProductosViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reg_productos);
        RegProductosFactory factory = Injection.provideRegistroViewModel(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(RegProductosViewModel.class);
        initAutoComplete();
        initViewModel();
        initView();
    }

    private void initView() {
        getSupportActionBar().setTitle("Registros Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.buttonFoto.setOnClickListener(v -> CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(RegProductosActivity.this));
        binding.btnRegistrar.setOnClickListener(v -> {
            mostrarProgressBar();
            String nombreProducto = binding.editTextNombreProducto.getText().toString();
            String descripcionProducto = binding.editTextDescripcionProducto.getText().toString();
            String minimoProducto = binding.editTextInventarioMinimoProducto.getText().toString();
            String codigoProducto = binding.editTextProductoCodigo.getText().toString();
            String loteCodigo = binding.editTextLoteCodigo.getText().toString();
            String stockPorCajas = binding.buttonCajas.getText().toString();
            String entradaPrecio = binding.editTextPrecioEntrada.getText().toString();
            String salidaPrecio = binding.editTextPrecioSalida.getText().toString();
            viewModel.onRegistraProducto(nombreProducto, descripcionProducto,
                    minimoProducto, codigoProducto, loteCodigo,
                    stockPorCajas, entradaPrecio, salidaPrecio);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    private void initViewModel() {
        viewModel.obtenerArgumentos(getIntent().getExtras());
        viewModel.mutableLiveDataListColor.observe(this, this::mostrarListaColor);
        viewModel.mutableLiveDataListMedida.observe(this, this::mostrarListaMedida);
        viewModel.mutableLiveDataListAlmacen.observe(this, this::mostrarListaAlmacen);
        viewModel.mutableLiveDataListMaterial.observe(this, this::mostrarListaMaterial);
        viewModel.mutableLiveDataListSuperficie.observe(this, this::mostrarListaSuperficie);
        viewModel.mutableLiveDataListMarca.observe(this, this::mostrarListaMarca);
        viewModel.mutableLiveDataListCategoria.observe(this, this::mostrarListaCategoria);
        viewModel.mutableLiveDataListTipologia.observe(this, this::mostrarListaTipologia);
        viewModel.mutableLiveDataStringFoto.observe(this, this::mostrarPathUri);
        viewModel.iniValidacionVista.observe(this, this::iniValidacionVista);
        viewModel.mutableLiveDataVista.observe(this, this::mostrarDatosVista);
        viewModel.initView();
        viewModel.mutableLiveDataInicial.observe(this, this::initViewData);
        viewModel.mutableLiveDataColorEdit.observe(this, this::initColorEdit);
        viewModel.mutableLiveDataMedidaEdit.observe(this, this::initColorMedidaEdit);
        viewModel.mutableLiveDataAlmacenEdit.observe(this, this::initColorAlmacenEdit);
        viewModel.mutableLiveDataMaterialEdit.observe(this, this::initMaterialEdit);
        viewModel.mutableLiveDataSuperficieEdit.observe(this, this::initSuperficieEdit);
        viewModel.mutableLiveDataMarcaEdit.observe(this, this::initMarcaEdit);
        viewModel.mutableLiveDataCategoriaEdit.observe(this, this::initCategoriaEdit);
        viewModel.mutableLiveDataTipologiaEdit.observe(this, this::initTipologiaEdit);
    }

    private void initTipologiaEdit(Integer integer) {
        try {
            binding.spinnerTipologia.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initCategoriaEdit(Integer integer) {
        try {
            binding.spinnerCategoria.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initMarcaEdit(Integer integer) {
        try {
            binding.spinnerMarca.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initSuperficieEdit(Integer integer) {
        try {
            binding.spinnerSuperficie.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initMaterialEdit(Integer integer) {
        try {
            binding.spinnerMaterial.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initColorAlmacenEdit(Integer integer) {
        try {
            binding.spinnerAlmacen.setSelection(integer);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initColorMedidaEdit(HashMap<String, String> stringStringHashMap) {
        try {
            String nombreColor = (String) stringStringHashMap.get("keyValor");
            binding.autoCompleteTextviewMedida.setText(nombreColor);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initColorEdit(HashMap<String, String> integerStringHashMap) {
        try {
            String nombreColor = (String) integerStringHashMap.get("keyValor");
            binding.autoCompleteTextviewColor.setText(nombreColor);
        } catch (Exception e) {
            Timber.d("Exception");
        }
    }

    private void initViewData(Object o) {
        try {
            ListProductosResponse.ClassProductos productoUi = (ListProductosResponse.ClassProductos) o;
            binding.editTextNombreProducto.setText(productoUi.getProducto_nombre());
            binding.editTextDescripcionProducto.setText(productoUi.getProducto_descripcion());
            binding.editTextInventarioMinimoProducto.setText(productoUi.getProducto_minimo());
            //binding.editTextProductoUnidad.setText(productoUi.getProducto_unidad());
            binding.editTextProductoCodigo.setText(productoUi.getProducto_cod_producto());
            binding.buttonCajas.setText(productoUi.getProducto_Stock());
            binding.editTextInventarioMinimoProducto.setText(productoUi.getProducto_Stock());
            binding.editTextLoteCodigo.setText(productoUi.getProducto_Lote());
            binding.buttonFoto.setText(productoUi.getProducto_image());
            binding.editTextPrecioEntrada.setText(productoUi.getProducto_precio_in());
            binding.editTextPrecioSalida.setText(productoUi.getProducto_precio_out());
            binding.btnRegistrar.setText("Editar Producto");
            mostrarOperacion(productoUi);
        } catch (Exception e) {
            Timber.d("algun Error");
        }
    }

    private void mostrarOperacion(ListProductosResponse.ClassProductos productoUi) {

        int stockXunidad = Integer.parseInt(productoUi.getMedida_descripcion());
        int stockCaja = Integer.parseInt(productoUi.getProducto_Stock());
        int unidadtotales = stockCaja * stockXunidad;
        String resultadoTotal = unidadtotales + " / " + productoUi.getMedida_descripcion();
        binding.buttonUnidad.setText(resultadoTotal);
    }


    private void mostrarDatosVista(HashMap<String, Object> stringObjectHashMap) {
        mostrarErrorTipoUnidad(stringObjectHashMap);
        mostrarTipoUnidad(stringObjectHashMap);
        validacionRegistro(stringObjectHashMap);

    }

    private void validacionRegistro(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            binding.progressBar.setVisibility(View.GONE);
            String tipoOperacion = (String) stringObjectHashMap.get("tipoOperacion");
            validarTipoOperacion(tipoOperacion);
            finish();
            Toast.makeText(RegProductosActivity.this, "Operacion Terminada", Toast.LENGTH_SHORT).show();
            ocultarProgressBar();
            Timber.d("RESPONSE_RETROFIT_FINALIZADO");
            return;
        }
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_ERROR) != null) {
            Timber.d("RESPONSE_RETROFIT_ERROR");
            ocultarProgressBar();
            return;
        }
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_SIN_INTERT) != null) {
            Timber.d("RESPONSE_RETROFIT_SIN_INTERT");
            ocultarProgressBar();
        }
    }

    private void validarTipoOperacion(String tipoOperacion) {
        try{
            switch (tipoOperacion) {
                case "actualizarsinfoto":
                    mostraMensaje("Producto Actualizado");
                    break;
                case "actualizarconfoto":
                    mostraMensaje("Producto Actualizado");
                    break;
                default:
                    mostraMensaje("Producto Regitrado con Éxito");
                    break;
            }
        }catch (Exception e){
            mostraMensaje("Producto Actualizado");
        }

    }

    private void mostrarTipoUnidad(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get("vistaResp") != null) {
            String respuesta = (String) stringObjectHashMap.get("vistaResp");
            mostrarUnidad(respuesta);
        }
        if (stringObjectHashMap.get("vistaRespNull") != null) {
            mostraMensaje("Ingrese Primero Stock Caja");
        }
    }

    private void mostrarUnidad(String respuesta) {
        binding.buttonUnidad.setText(respuesta);
    }

    private void mostrarErrorTipoUnidad(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get("vistaRespError") != null) {
            String error = (String) stringObjectHashMap.get("vistaRespError");
            mostraMensaje(error);
            ocultarProgressBar();
        }
    }

    private void mostraMensaje(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void iniValidacionVista(HashMap<String, Object> stringObjectHashMap) {
        viewModel.iniValidacionVistaM(stringObjectHashMap);
    }

    private void mostrarPathUri(Uri uri) {
        binding.buttonFoto.setText(uri.getPath());
    }

    private void mostrarListaColor(List<ModelDefault> modelDefaults) {
        ArrayAdapterDefault adapterColor = new ArrayAdapterDefault(this, modelDefaults);
        binding.autoCompleteTextviewColor.setAdapter(adapterColor);
        viewModel.consultarColor(modelDefaults);
        binding.autoCompleteTextviewColor.setOnItemClickListener((parent, view, position, id) -> {
            try {
                ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                viewModel.onSpinnerTipoColor(modelDefault);
            } catch (Exception e) {
                Timber.d("Exeption autoCompleteTextviewColor");
            }
        });
    }

    private void mostrarListaMedida(List<ModelDefault> modelDefaults) {
        ArrayAdapterDefault adapterMedida = new ArrayAdapterDefault(this, modelDefaults);
        binding.autoCompleteTextviewMedida.setAdapter(adapterMedida);

        viewModel.consultarMedida(modelDefaults);
        binding.autoCompleteTextviewMedida.setOnItemClickListener((parent, view, position, id) -> {
            try {
                ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                viewModel.onSpinnerTipoMedida(modelDefault);
                String stockPorCajas = binding.buttonCajas.getText().toString();
                viewModel.refrescarCajas(stockPorCajas);
            } catch (Exception e) {
                Timber.d("Exeption autoCompleteTextviewMedida");
            }
        });
    }

    private void mostrarListaAlmacen(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultAlmacen = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerAlmacen.setAdapter(spinnerDefaultAlmacen);
        viewModel.consultarAlmacen(modelDefaults);

        binding.spinnerAlmacen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoAlmacen(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerAlmacen");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void mostrarListaMaterial(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultMaterial = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerMaterial.setAdapter(spinnerDefaultMaterial);
        viewModel.consultarMaterial(modelDefaults);
        binding.spinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoMaterial(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerMaterial");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void mostrarListaSuperficie(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultSuperficie = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerSuperficie.setAdapter(spinnerDefaultSuperficie);
        viewModel.consultarSuperficie(modelDefaults);
        binding.spinnerSuperficie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoSuperficie(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerSuperficie");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void mostrarListaMarca(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultMarca = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerMarca.setAdapter(spinnerDefaultMarca);
        viewModel.consultarMarca(modelDefaults);
        binding.spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoMarca(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerSuperficie");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void mostrarListaCategoria(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultCategoria = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerCategoria.setAdapter(spinnerDefaultCategoria);
        viewModel.consultarCategoria(modelDefaults);
        binding.spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoCategoria(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerSuperficie");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void mostrarListaTipologia(List<ModelDefault> modelDefaults) {
        SpinnerDefault spinnerDefaultTipologia = new SpinnerDefault(this,
                R.layout.custom_spinner_default, modelDefaults);
        binding.spinnerTipologia.setAdapter(spinnerDefaultTipologia);
        viewModel.consultarTipologia(modelDefaults);
        binding.spinnerTipologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ModelDefault modelDefault = (ModelDefault) parent.getAdapter().getItem(position);
                    viewModel.onSpinnerTipoTipologia(modelDefault);
                } catch (Exception e) {
                    Timber.d("Exeption spinnerSuperficie");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ocultarProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void mostrarProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void initAutoComplete() {
        viewModel.mostrarListaColor();
        viewModel.mostrarListaMedida();
        viewModel.mostrarListaAlmacen();
        viewModel.mostrarListaMaterial();
        viewModel.mostrarListaSuperficie();
        viewModel.mostrarListaMarca();
        viewModel.mostrarListaCategoria();
        viewModel.mostrarListaTipologia();
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
