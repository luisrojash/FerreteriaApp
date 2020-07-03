package com.example.ferreteriaapp.productos.registro;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.base.AppExecutors;
import com.example.ferreteriaapp.db.FerreDB;
import com.example.ferreteriaapp.model.Almacen;
import com.example.ferreteriaapp.model.Categoria;
import com.example.ferreteriaapp.model.Color;
import com.example.ferreteriaapp.model.Material;
import com.example.ferreteriaapp.model.Medida;
import com.example.ferreteriaapp.model.Proveedor;
import com.example.ferreteriaapp.model.Superficie;
import com.example.ferreteriaapp.model.Tipologia;
import com.example.ferreteriaapp.productos.registro.service.RegProductosResponse;
import com.example.ferreteriaapp.productos.registro.service.RegProductosService;
import com.example.ferreteriaapp.productos.registro.ui.ModelDefault;
import com.example.ferreteriaapp.util.AppConstants;
import com.example.ferreteriaapp.util.Utilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RegProductosRepository {

    private AppExecutors appExecutors;
    private RegProductosService service;
    private FerreDB db;

    public RegProductosRepository(AppExecutors appExecutors, RegProductosService service, FerreDB db) {
        this.appExecutors = appExecutors;
        this.service = service;
        this.db = db;
    }

    void obtenerListaColor(MutableLiveData<List<ModelDefault>> mutableLiveDataListColor) {
        appExecutors.networkIO().execute(() -> {
            List<ModelDefault> modelDefaultList = new ArrayList<>();
            for (Color color : db.colorDao().obtenerListaColor()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(color.getColor_id() + "");
                modelDefault.setNombreModel(color.getColor_nombre());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListColor.postValue(modelDefaultList);
        });
    }

    void obtenerListaMedida(MutableLiveData<List<ModelDefault>> mutableLiveDataListMedida) {
        appExecutors.networkIO().execute(() -> {
            List<ModelDefault> modelDefaultList = new ArrayList<>();
            for (Medida medida : db.medidaDao().obtenerListaMedida()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(medida.getMedida_id() + "");
                modelDefault.setNombreModel(medida.getMedida_medida());
                modelDefault.setMedidaDescripcion(medida.getMedida_descripcion());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListMedida.postValue(modelDefaultList);
        });
    }

    void mostrarListaAlmacen(MutableLiveData<List<ModelDefault>> mutableLiveDataListAlmacen) {

        appExecutors.networkIO().execute(() -> {
            List<ModelDefault> modelDefaultList = new ArrayList<>();
            ModelDefault modelDefaultTitulo = new ModelDefault();
            modelDefaultTitulo.setIdModel("0");
            modelDefaultTitulo.setNombreModel("Seleccione Almacen");
            modelDefaultList.add(modelDefaultTitulo);
            for (Almacen almacen :
                    db.almacenDao().obtenerListaAlmacen()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(almacen.getAlmacen_id() + "");
                modelDefault.setNombreModel(almacen.getAlmacen_nombre());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListAlmacen.postValue(modelDefaultList);
        });
    }

    void mostrarListaMaterial(MutableLiveData<List<ModelDefault>> mutableLiveDataListMaterial) {
        List<ModelDefault> modelDefaultList = new ArrayList<>();
        ModelDefault modelDefaultTitulo = new ModelDefault();
        modelDefaultTitulo.setIdModel("0");
        modelDefaultTitulo.setNombreModel("Seleccione Material");
        modelDefaultList.add(modelDefaultTitulo);

        appExecutors.networkIO().execute(() -> {
            for (Material material :
                    db.materialDao().obtenerListaMaterial()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(material.getMaterial_id() + "");
                modelDefault.setNombreModel(material.getMaterial_nombre());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListMaterial.postValue(modelDefaultList);
        });


    }

    void mostrarListaSuperficie(MutableLiveData<List<ModelDefault>> mostrarListaSuperficie) {
        List<ModelDefault> modelDefaultList = new ArrayList<>();
        ModelDefault modelDefaultTitulo = new ModelDefault();
        modelDefaultTitulo.setIdModel("0");
        modelDefaultTitulo.setNombreModel("Seleccione Superficie");
        modelDefaultList.add(modelDefaultTitulo);

        appExecutors.networkIO().execute(() -> {
            for (Superficie superficie :
                    db.superficieDao().obtenerListaSuperficie()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(superficie.getSuperficie_id() + "");
                modelDefault.setNombreModel(superficie.getSuperficie_nombre());
                modelDefaultList.add(modelDefault);
            }
            mostrarListaSuperficie.postValue(modelDefaultList);
        });

    }

    void mostrarListaMarca(MutableLiveData<List<ModelDefault>> mutableLiveDataListMarca) {
        List<ModelDefault> modelDefaultList = new ArrayList<>();
        ModelDefault modelDefaultTitulo = new ModelDefault();
        modelDefaultTitulo.setIdModel("0");
        modelDefaultTitulo.setNombreModel("Seleccione Marca");
        modelDefaultList.add(modelDefaultTitulo);

        appExecutors.networkIO().execute(() -> {
            for (Proveedor proveedor :
                    db.proveedorDao().obtenerListaMarca()) {
                if (proveedor.getProveedor_marca() != null) {
                    ModelDefault modelDefault = new ModelDefault();
                    modelDefault.setIdModel(proveedor.getProveedor_marca_id());
                    modelDefault.setNombreModel(proveedor.getProveedor_marca());
                    modelDefaultList.add(modelDefault);
                }
            }
            mutableLiveDataListMarca.postValue(modelDefaultList);
        });
    }

    void mostrarListaCategoria(MutableLiveData<List<ModelDefault>> mutableLiveDataListCategoria) {
        List<ModelDefault> modelDefaultList = new ArrayList<>();
        ModelDefault modelDefaultTitulo = new ModelDefault();
        modelDefaultTitulo.setIdModel("0");
        modelDefaultTitulo.setNombreModel("Seleccione Categoria");
        modelDefaultList.add(modelDefaultTitulo);
        appExecutors.networkIO().execute(() -> {
            for (Categoria categoria :
                    db.categoriaDao().obtenerListaCategoria()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(categoria.getCategoria_id() + "");
                modelDefault.setNombreModel(categoria.getCategoria_nombre());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListCategoria.postValue(modelDefaultList);
        });
    }

    void mostrarListaTipologia(MutableLiveData<List<ModelDefault>> mutableLiveDataListTipologia) {
        List<ModelDefault> modelDefaultList = new ArrayList<>();
        ModelDefault modelDefaultTitulo = new ModelDefault();
        modelDefaultTitulo.setIdModel("0");
        modelDefaultTitulo.setNombreModel("Seleccione Tipologia");
        modelDefaultList.add(modelDefaultTitulo);

        appExecutors.networkIO().execute(() -> {
            for (Tipologia tipologia :
                    db.tipologiaDao().obtenerListaTipologia()) {
                ModelDefault modelDefault = new ModelDefault();
                modelDefault.setIdModel(tipologia.getTipologia_id() + "");
                modelDefault.setNombreModel(tipologia.getTipologia_nombre());
                modelDefaultList.add(modelDefault);
            }
            mutableLiveDataListTipologia.postValue(modelDefaultList);
        });
    }

    public void initRegistroProducto(RequestBody requestFile, RequestBody requestNombreProducto, RequestBody requestDescripcionProducto,
                                     RequestBody requestMinimoProducto, RequestBody requestStockPorCaja, RequestBody requestCodigoProducto,
                                     RequestBody requestLoteProducto, RequestBody requestEntradaPrecio, RequestBody requestSalidaPrecio,
                                     RequestBody requestSelectedColor, RequestBody requestSelectedMedida, RequestBody requestSelectedAlmacen,
                                     RequestBody requestSelectedMaterial, RequestBody requestSelectedSuperficie, RequestBody requestSelectedMarca,
                                     RequestBody requestSelectedCategoria, RequestBody requestSelectedTipologia, RequestBody requestTipoOperacion,
                                     RequestBody requestProductoId, MutableLiveData<HashMap<String, Object>> mutableLiveDataVista) {
        String tipoOperacion = Utilidades.bodyToString(requestTipoOperacion);
        Timber.d("tipoOperacion : %s ", tipoOperacion);
        switch (tipoOperacion) {
            case "actualizarsinfoto":
                registroSinFotoProducto(requestNombreProducto, requestDescripcionProducto,
                        requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                        requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                        requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                        requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                        requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion, requestProductoId, mutableLiveDataVista);
                break;
            case "actualizarconfoto":
                registroConFotoProducto(requestFile, requestNombreProducto, requestDescripcionProducto,
                        requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                        requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                        requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                        requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                        requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion,
                        requestProductoId, mutableLiveDataVista);
                break;
            default:
                registroProducto(requestFile, requestNombreProducto, requestDescripcionProducto,
                        requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                        requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                        requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                        requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                        requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion, mutableLiveDataVista, tipoOperacion);
                break;
        }

    }

    private void registroConFotoProducto(RequestBody requestFile, RequestBody requestNombreProducto, RequestBody requestDescripcionProducto, RequestBody requestMinimoProducto, RequestBody requestStockPorCaja, RequestBody requestCodigoProducto, RequestBody requestLoteProducto, RequestBody requestEntradaPrecio, RequestBody requestSalidaPrecio, RequestBody requestSelectedColor, RequestBody requestSelectedMedida, RequestBody requestSelectedAlmacen, RequestBody requestSelectedMaterial, RequestBody requestSelectedSuperficie, RequestBody requestSelectedMarca, RequestBody requestSelectedCategoria, RequestBody requestSelectedTipologia, RequestBody requestTipoOperacion, RequestBody requestProductoId, MutableLiveData<HashMap<String, Object>> mutableLiveDataVista) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        service.registrarConFotoProducto(requestFile, requestTipoOperacion, requestNombreProducto, requestDescripcionProducto,
                requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                requestSelectedCategoria, requestSelectedTipologia, requestProductoId).enqueue(new Callback<RegProductosResponse>() {
            @Override
            public void onResponse(Call<RegProductosResponse> call, Response<RegProductosResponse> response) {
                if (response.isSuccessful()) {
                    RegProductosResponse response1 = response.body();
                    if (response1.getError()) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataVista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, response1.getMessage());
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<RegProductosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataVista.postValue(stringObjectHashMap);
            }
        });
    }


    private void registroSinFotoProducto(RequestBody requestNombreProducto, RequestBody requestDescripcionProducto,
                                         RequestBody requestMinimoProducto, RequestBody requestStockPorCaja,
                                         RequestBody requestCodigoProducto, RequestBody requestLoteProducto,
                                         RequestBody requestEntradaPrecio, RequestBody requestSalidaPrecio,
                                         RequestBody requestSelectedColor, RequestBody requestSelectedMedida,
                                         RequestBody requestSelectedAlmacen, RequestBody requestSelectedMaterial,
                                         RequestBody requestSelectedSuperficie, RequestBody requestSelectedMarca,
                                         RequestBody requestSelectedCategoria, RequestBody requestSelectedTipologia,
                                         RequestBody requestTipoOperacion, RequestBody requestProductoId,
                                         MutableLiveData<HashMap<String, Object>> mutableLiveDataVista) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        service.registrarSinFotoProducto(requestTipoOperacion, requestNombreProducto, requestDescripcionProducto,
                requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                requestSelectedCategoria, requestSelectedTipologia, requestProductoId).enqueue(new Callback<RegProductosResponse>() {
            @Override
            public void onResponse(Call<RegProductosResponse> call, Response<RegProductosResponse> response) {
                if (response.isSuccessful()) {
                    RegProductosResponse response1 = response.body();
                    if (response1.getError()) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataVista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, response1.getMessage());
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<RegProductosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataVista.postValue(stringObjectHashMap);
            }
        });
    }

    private void registroProducto(RequestBody requestFile, RequestBody requestNombreProducto,
                                  RequestBody requestDescripcionProducto, RequestBody requestMinimoProducto,
                                  RequestBody requestStockPorCaja, RequestBody requestCodigoProducto,
                                  RequestBody requestLoteProducto, RequestBody requestEntradaPrecio,
                                  RequestBody requestSalidaPrecio, RequestBody requestSelectedColor,
                                  RequestBody requestSelectedMedida, RequestBody requestSelectedAlmacen,
                                  RequestBody requestSelectedMaterial, RequestBody requestSelectedSuperficie,
                                  RequestBody requestSelectedMarca, RequestBody requestSelectedCategoria,
                                  RequestBody requestSelectedTipologia, RequestBody requestTipoOperacion,
                                  MutableLiveData<HashMap<String, Object>> mutableLiveDataVista,
                                  String tipoOperacion) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        service.registrarProducto(requestFile, requestTipoOperacion, requestNombreProducto, requestDescripcionProducto,
                requestMinimoProducto, requestStockPorCaja, requestCodigoProducto,
                requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen,
                requestSelectedMaterial, requestSelectedSuperficie, requestSelectedMarca,
                requestSelectedCategoria, requestSelectedTipologia).enqueue(new Callback<RegProductosResponse>() {
            @Override
            public void onResponse(Call<RegProductosResponse> call, Response<RegProductosResponse> response) {
                if (response.isSuccessful()) {
                    RegProductosResponse response1 = response.body();
                    if (response1.getError()) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        stringObjectHashMap.put("tipoOperacion", tipoOperacion);
                        mutableLiveDataVista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, response1.getMessage());
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataVista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<RegProductosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataVista.postValue(stringObjectHashMap);
            }
        });
    }
}
