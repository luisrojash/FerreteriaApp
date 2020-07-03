package com.example.ferreteriaapp.splash;

import android.util.Log;

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
import com.example.ferreteriaapp.splash.service.SplashResponse;
import com.example.ferreteriaapp.splash.service.SplashService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SplashRepository {

    private AppExecutors appExecutors;
    private SplashService service;
    private FerreDB db;

    public SplashRepository(AppExecutors appExecutors, SplashService splashService, FerreDB db) {
        this.appExecutors = appExecutors;
        this.service = splashService;
        this.db = db;
    }

    void onCargarDataInicial(MutableLiveData<HashMap<String, Object>> hashMapCargarDataPrincipal) {
        HashMap<String, Object> hashMapPeticion = new HashMap<>();
        service.obtenerDataPrincipal().enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                if (response.isSuccessful()) {
                    SplashResponse resultado = response.body();
                    String estado = "RESPONSE_RETROFIT_FINALIZADO";
                    List<Categoria> categoriaList = resultado.getCategoriaLista();
                    List<Proveedor> proveedorList = resultado.getProveedorLista();
                    List<Tipologia> tipologiaList = resultado.getTipologiaLista();
                    /*Tablas Agregadas 08/06/2019*/
                    List<Color> colorLista = resultado.getColorLista();
                    List<Material> materialList = resultado.getMaterialLista();
                    List<Medida> medidaList = resultado.getMedidaLista();
                    List<Superficie> superficieLista = resultado.getSuperficieLista();
                    List<Almacen> almacenLista = resultado.getAlmacenLista();

                    if (validarTryCatchCategoria(categoriaList).equals(AppConstants.ERROR_IMPORT_CATEGORIA)) {
                        estado = AppConstants.ERROR_IMPORT_CATEGORIA;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchProveedor(proveedorList).equals(AppConstants.ERROR_IMPORT_PROVEEDOR)) {
                        estado = AppConstants.ERROR_IMPORT_PROVEEDOR;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchTipologia(tipologiaList).equals(AppConstants.ERROR_IMPORT_TIPOLOGIA)) {
                        estado = AppConstants.ERROR_IMPORT_TIPOLOGIA;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchColor(colorLista).equals(AppConstants.ERROR_IMPORT_COLOR)) {
                        estado = AppConstants.ERROR_IMPORT_COLOR;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchMaterial(materialList).equals(AppConstants.ERROR_IMPORT_MATERIAL)) {
                        estado = AppConstants.ERROR_IMPORT_MATERIAL;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchMedida(medidaList).equals(AppConstants.ERROR_IMPORT_MEDIDA)) {
                        estado = AppConstants.ERROR_IMPORT_MEDIDA;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchSuperficie(superficieLista).equals(AppConstants.ERROR_IMPORT_SUPERFICIE)) {
                        estado = AppConstants.ERROR_IMPORT_SUPERFICIE;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }
                    if (validarTryCatchAlmacen(almacenLista).equals(AppConstants.ERROR_IMPORT_ALMACEN)) {
                        estado = AppConstants.ERROR_IMPORT_ALMACEN;
                        hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
                        hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                        return;
                    }

                    hashMapPeticion.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, estado);
                    hashMapCargarDataPrincipal.postValue(hashMapPeticion);

                } else {
                    hashMapPeticion.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    hashMapCargarDataPrincipal.postValue(hashMapPeticion);
                }
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                hashMapPeticion.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                hashMapCargarDataPrincipal.postValue(hashMapPeticion);
            }
        });
    }


    private String validarTryCatchAlmacen(List<Almacen> almacenList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.almacenDao().insertList(almacenList));
            Timber.d("Importo Correcto : almacenDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_ALMACEN;
        }
        return estado;
    }

    private String validarTryCatchSuperficie(List<Superficie> superficieLista) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.superficieDao().insertList(superficieLista));
            Timber.d("Importo Correcto : superficieDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_SUPERFICIE;
        }
        return estado;
    }

    private String validarTryCatchMedida(List<Medida> medidaList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.medidaDao().insertList(medidaList));
            Timber.d("Importo Correcto : medidaDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_MEDIDA;
        }
        return estado;
    }

    private String validarTryCatchMaterial(List<Material> materialList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.materialDao().insertList(materialList));
            Timber.d("Importo Correcto : materialDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_MATERIAL;
        }
        return estado;
    }

    private String validarTryCatchColor(List<Color> colorLista) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.colorDao().insertList(colorLista));
            Timber.d("Importo Correcto : colorDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_COLOR;
        }
        return estado;
    }

    private String validarTryCatchTipologia(List<Tipologia> tipologiaList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.tipologiaDao().insertList(tipologiaList));
            Timber.d("Importo Correcto : tipologiaDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_TIPOLOGIA;
        }
        return estado;
    }

    private String validarTryCatchProveedor(List<Proveedor> proveedorList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.proveedorDao().insertList(proveedorList));
            Timber.d("Importo Correcto : proveedorDao ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_PROVEEDOR;
        }
        return estado;
    }

    private String validarTryCatchCategoria(List<Categoria> categoriaList) {
        String estado = "";
        try {
            appExecutors.networkIO()
                    .execute(() -> db.categoriaDao().insertList(categoriaList));
            Timber.d("Importo Correcto : Categoria ");
        } catch (Exception e) {
            estado = AppConstants.ERROR_IMPORT_CATEGORIA;
        }
        return estado;
    }
}
