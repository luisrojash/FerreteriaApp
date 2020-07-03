package com.example.ferreteriaapp.operaciones;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.base.AppExecutors;
import com.example.ferreteriaapp.buscador.modelo.BuscarProductosUi;
import com.example.ferreteriaapp.db.FerreDB;
import com.example.ferreteriaapp.lote.modelo.LoteUi;
import com.example.ferreteriaapp.operaciones.model.ModelDefault;
import com.example.ferreteriaapp.operaciones.service.OpeResponse;
import com.example.ferreteriaapp.operaciones.service.OperacionesResponse;
import com.example.ferreteriaapp.operaciones.service.OperacionesService;
import com.example.ferreteriaapp.model.Almacen;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class OperacionesRepository {

    private OperacionesService service;
    private FerreDB db;
    private AppExecutors appExecutors;

    public OperacionesRepository(OperacionesService service, FerreDB db, AppExecutors appExecutors) {
        this.service = service;
        this.db = db;
        this.appExecutors = appExecutors;
    }

    public void buscarProductos(String buscarTexto, MutableLiveData<HashMap<String, Object>> responseMutableLiveData) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerLista(buscarTexto).enqueue(new Callback<OperacionesResponse>() {
            @Override
            public void onResponse(Call<OperacionesResponse> call, Response<OperacionesResponse> response) {
                if (response.isSuccessful()) {
                    OperacionesResponse operacionesResponse = response.body();
                    if (operacionesResponse.getListaProductos() == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, operacionesResponse.getMensaje());
                        responseMutableLiveData.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, actualizarLista(operacionesResponse.getListaProductos()));
                    responseMutableLiveData.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    responseMutableLiveData.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<OperacionesResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                responseMutableLiveData.postValue(stringObjectHashMap);
            }
        });
    }

    private Object actualizarLista(List<OperacionesResponse.ProductosResp> listaProductos) {
        List<BuscarProductosUi> productosUiList = new ArrayList<>();
        for (OperacionesResponse.ProductosResp resp :
                listaProductos) {
            BuscarProductosUi buscarProductosUi = new BuscarProductosUi();
            buscarProductosUi.setIdProductos(resp.getProducto_id());
            buscarProductosUi.setNombreProductos(resp.getProducto_nombre());
            buscarProductosUi.setAlmacen_id(resp.getAlmacen_id());
            buscarProductosUi.setInventarioMinimo(resp.getProducto_minimo());
            buscarProductosUi.setPrecioIn(resp.getProducto_precio_in());
            buscarProductosUi.setPrecioOut(resp.getProducto_precio_out());
            buscarProductosUi.setProductoUnidad(resp.getProducto_unidad());
            buscarProductosUi.setProductoCodigo(resp.getProducto_cod_producto());
            buscarProductosUi.setProductoStockInicial(resp.getProducto_stock_inicial());
            buscarProductosUi.setProveedorCodigo(resp.getProveedor_id());
            buscarProductosUi.setColorCodigo(resp.getColor_color_id());
            buscarProductosUi.setCategoriaCatogia(resp.getCategoria_id());
            buscarProductosUi.setTipologia(resp.getTipologia_id());
            buscarProductosUi.setMedidadCodigo(resp.getMedida_medida_id());
            buscarProductosUi.setMedidadDescripcion(resp.getMedida_descripcion());
            buscarProductosUi.setMaterialCodigo(resp.getMaterial_material_id());
            buscarProductosUi.setSuperficieCodigo(resp.getSuperficie_superficie_id());
            buscarProductosUi.setLoteCodigo(resp.getLote_id());
            buscarProductosUi.setM2(resp.getM2());
            buscarProductosUi.setProductoImagen(resp.getProducto_imagen());
            buscarProductosUi.setDescripcionProductos(resp.getProducto_descripcion());
            productosUiList.add(buscarProductosUi);
        }
        return productosUiList;
    }

    public void obtenerListaAlmacen(MutableLiveData<HashMap<String, Object>> responseAlmacen) {
        appExecutors.networkIO().execute(() -> {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            List<ModelDefault> modelDefaultList = new ArrayList<>();
            for (Almacen almacen1
                    : db.almacenDao().obtenerListaAlmacen()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(almacen1.getAlmacen_id());
                modelDefault.setNombreModel(almacen1.getAlmacen_nombre());
                modelDefault.setIdAlamacen(almacen1.getAlmacen_id());
                modelDefaultList.add(modelDefault);
            }
            stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, modelDefaultList);
            responseAlmacen.postValue(stringObjectHashMap);
        });

    }

    void obtenerLote(String idProductos, String idAlamacen, String loteCodigo, MutableLiveData<HashMap<String, Object>> responseLote) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerLote(idProductos, idAlamacen, loteCodigo).enqueue(new Callback<OperacionesResponse>() {
            @Override
            public void onResponse(Call<OperacionesResponse> call, Response<OperacionesResponse> response) {
                if (response.isSuccessful()) {
                    OperacionesResponse operacionesResponse = response.body();
                    if (operacionesResponse.getLoteLista() == null) {
                        Timber.d("obtenerLote == NULL");
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, operacionesResponse.getMensaje());
                        responseLote.postValue(stringObjectHashMap);
                        return;
                    }
                    Timber.d("obtenerLote::Finalizado");
                    stringObjectHashMap.put("listaLote", actualizarListaLote(operacionesResponse.getLoteLista()));
                    responseLote.postValue(stringObjectHashMap);
                } else {
                    Timber.d("obtenerLote == RESPONSE_RETROFIT_ERROR");
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    responseLote.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<OperacionesResponse> call, Throwable t) {
                Timber.d("obtenerLote == RESPONSE_RETROFIT_SIN_INTERT");
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                responseLote.postValue(stringObjectHashMap);
            }
        });

    }

    private List<LoteUi> actualizarListaLote(List<OperacionesResponse.LoteResp> listaLote) {
        List<LoteUi> loteUiList = new ArrayList<>();
        for (OperacionesResponse.LoteResp resp :
                listaLote) {
            LoteUi loteUi = new LoteUi();
            loteUi.setLoteCodigo(resp.getLoteCodigo());
            loteUi.setStockProducto(resp.getStockProducto());
            loteUi.setUnidadProducto(resp.getUnidadProducto());
            loteUiList.add(loteUi);
        }
        return loteUiList;
    }

    void initGuardarListaOperacion(String jsonOperacion, String tipoOperacion, MutableLiveData<HashMap<String, Object>> responseFabAgregar) {

        if ("Entrada".equals(tipoOperacion)) {
            Timber.d("initGuardarListaOperacion ");
            initGuardarTipoEntrada(jsonOperacion, tipoOperacion, responseFabAgregar);
        } else if ("Salida".equals(tipoOperacion)) {
            initGuardarTipoSalida(jsonOperacion, tipoOperacion, responseFabAgregar);
        } else if ("Devoluciones".equals(tipoOperacion)) {
            initGuardarTipoDevoluciones(jsonOperacion, tipoOperacion, responseFabAgregar);
           // initGuardarTipoSalida(jsonOperacion, "Salida", responseFabAgregar);
        }
    }


    private void initGuardarTipoDevoluciones(String jsonOperacion, String tipoOperacion, MutableLiveData<HashMap<String, Object>> responseFabAgregar) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.registrarOperacionDevoluciones(jsonOperacion, tipoOperacion).enqueue(new Callback<OpeResponse>() {
            @Override
            public void onResponse(Call<OpeResponse> call, Response<OpeResponse> response) {
                if (response.isSuccessful()) {
                    OpeResponse operacionesResponse = response.body();
                    if (operacionesResponse.getMensaje() == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, operacionesResponse.getMensaje());
                        responseFabAgregar.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, operacionesResponse.getMensaje());
                    responseFabAgregar.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    responseFabAgregar.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<OpeResponse> call, Throwable t) {
                Timber.d("onFailure: %s ", t.getMessage());
                if(t.getMessage()!=null){
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, "Operacion exitosa");
                    responseFabAgregar.postValue(stringObjectHashMap);
                    return;
                }
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                responseFabAgregar.postValue(stringObjectHashMap);
            }
        });
    }

    private void initGuardarTipoSalida(String jsonOperacion, String tipoOperacion, MutableLiveData<HashMap<String, Object>> responseFabAgregar) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.registrarOperacionSalida(jsonOperacion, tipoOperacion).enqueue(new Callback<OpeResponse>() {
            @Override
            public void onResponse(Call<OpeResponse> call, Response<OpeResponse> response) {
                if (response.isSuccessful()) {
                    OpeResponse operacionesResponse = response.body();
                    if (operacionesResponse.getMensaje() == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, operacionesResponse.getMensaje());
                        responseFabAgregar.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, operacionesResponse.getMensaje());
                    responseFabAgregar.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    responseFabAgregar.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<OpeResponse> call, Throwable t) {
                Timber.d("onFailure: %s ", t.getMessage());

                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                responseFabAgregar.postValue(stringObjectHashMap);
            }
        });
    }

    private void initGuardarTipoEntrada(String jsonOperacion, String tipoOperacion, MutableLiveData<HashMap<String, Object>> responseFabAgregar) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.registrarOperacionEntrada(jsonOperacion, tipoOperacion).enqueue(new Callback<OpeResponse>() {
            @Override
            public void onResponse(Call<OpeResponse> call, Response<OpeResponse> response) {
                if (response.isSuccessful()) {
                    OpeResponse operacionesResponse = response.body();
                    if (operacionesResponse.getMensaje() == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, operacionesResponse.getMensaje());
                        responseFabAgregar.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, operacionesResponse.getMensaje());
                    responseFabAgregar.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    responseFabAgregar.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<OpeResponse> call, Throwable t) {
                Timber.d("onFailure: %s ", t.getMessage());
                if(t.getMessage()!=null){
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, "Operacion exitosa");
                    responseFabAgregar.postValue(stringObjectHashMap);
                    return;
                }
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                responseFabAgregar.postValue(stringObjectHashMap);
            }
        });
    }


}
