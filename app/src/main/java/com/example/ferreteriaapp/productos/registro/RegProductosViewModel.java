package com.example.ferreteriaapp.productos.registro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ferreteriaapp.productos.registro.ui.ModelDefault;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;
import com.example.ferreteriaapp.util.Utilidades;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

class RegProductosViewModel extends ViewModel {

    MutableLiveData<List<ModelDefault>> mutableLiveDataListColor = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListMedida = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListAlmacen = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListMaterial = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListSuperficie = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListMarca = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListCategoria = new MutableLiveData<>();
    MutableLiveData<List<ModelDefault>> mutableLiveDataListTipologia = new MutableLiveData<>();

    MutableLiveData<HashMap<String, Object>> iniValidacionVista = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> mutableLiveDataVista = new MutableLiveData<>();

    MutableLiveData<Uri> mutableLiveDataStringFoto = new MutableLiveData<>();


    private RegProductosRepository repository;
    private List<File> mFileList;
    private Context context;
    private Uri uriComprimida;
    private ModelDefault onSpinnerTipoMedida;
    private ModelDefault onSpinnerTipoColor;
    private ModelDefault onSpinnerTipoAlmacen;
    private ModelDefault onSpinnerTipoMaterial;
    private ModelDefault onSpinnerTipoMarca;
    private ModelDefault onSpinnerTipoSuperficie;
    private ModelDefault onSpinnerTipoCategoria;
    private ModelDefault onSpinnerTipoTipologia;
    private String stockPorCaja;

    private ListProductosResponse.ClassProductos productoUi;

    RegProductosViewModel(RegProductosRepository repository, Context context) {
        this.repository = repository;
        this.context = context;
        mFileList = new ArrayList<>();

    }

    public void obtenerArgumentos(Bundle extras) {
        if (extras == null) return;

        productoUi = extras.getParcelable("productoUi");
        if (productoUi != null) {
            Timber.d("productoUi : %s", productoUi.getProducto_nombre());
        }

    }

    public void mostrarListaColor() {
        repository.obtenerListaColor(mutableLiveDataListColor);
    }

    public void mostrarListaMedida() {
        repository.obtenerListaMedida(mutableLiveDataListMedida);
    }

    public void mostrarListaAlmacen() {
        repository.mostrarListaAlmacen(mutableLiveDataListAlmacen);
    }

    public void mostrarListaMaterial() {
        repository.mostrarListaMaterial(mutableLiveDataListMaterial);
    }

    public void mostrarListaSuperficie() {
        repository.mostrarListaSuperficie(mutableLiveDataListSuperficie);
    }

    public void mostrarListaMarca() {
        repository.mostrarListaMarca(mutableLiveDataListMarca);
    }

    public void mostrarListaCategoria() {
        repository.mostrarListaCategoria(mutableLiveDataListCategoria);
    }

    public void mostrarListaTipologia() {
        repository.mostrarListaTipologia(mutableLiveDataListTipologia);
    }

    public void onSpinnerTipoColor(ModelDefault modelDefault) {
        this.onSpinnerTipoColor = modelDefault;
    }

    void onSpinnerTipoMedida(ModelDefault modelDefault) {
        this.onSpinnerTipoMedida = modelDefault;
        if (onSpinnerTipoMedida != null) {
            HashMap<String, Object> hashMapResponseSpinner = new HashMap<>();
            hashMapResponseSpinner.put("vista", onSpinnerTipoMedida.getMedidaDescripcion());
            iniValidacionVista.postValue(hashMapResponseSpinner);
        }
    }

    public void onSpinnerTipoAlmacen(ModelDefault modelDefault) {
        this.onSpinnerTipoAlmacen = modelDefault;
    }

    public void onSpinnerTipoMaterial(ModelDefault modelDefault) {
        this.onSpinnerTipoMaterial = modelDefault;
    }

    public void onSpinnerTipoSuperficie(ModelDefault modelDefault) {
        this.onSpinnerTipoSuperficie = modelDefault;
    }

    public void onSpinnerTipoMarca(ModelDefault modelDefault) {
        this.onSpinnerTipoMarca = modelDefault;
    }

    public void onSpinnerTipoCategoria(ModelDefault modelDefault) {
        this.onSpinnerTipoCategoria = modelDefault;
    }

    public void onSpinnerTipoTipologia(ModelDefault modelDefault) {
        this.onSpinnerTipoTipologia = modelDefault;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mFileList.clear();
                Uri resultUri = result.getUri();
                mFileList.add(new File(resultUri.getPath()));
                comprimirImagenLista();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Timber.d("error::Imagen");
            }
        }
    }

    private void comprimirImagenLista() {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(context, mFileList)
                .putGear(Luban.THIRD_GEAR)
                .launch(new OnMultiCompressListener() {
                    @Override
                    public void onStart() {
                        Timber.i("start");
                    }

                    @Override
                    public void onSuccess(List<File> fileList) {
                        int size = fileList.size();
                        while (size-- > 0) {
                            uriComprimida = Uri.fromFile(fileList.get(size));
                            Timber.d("uriComprimida : " + uriComprimida.getPath());
                            mutableLiveDataStringFoto.postValue(uriComprimida);
                            // if (view != null) view.mostrarPathUri(uriComprimida);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    void iniValidacionVistaM(HashMap<String, Object> stringObjectHashMap) {

        // String statusVista = (String) stringObjectHashMap.get("vista");
        if (stringObjectHashMap.get("vista") != null) {
            String statusVista = (String) stringObjectHashMap.get("vista");
            Timber.d("respuesta: %s ", statusVista);
            actualizacionMedida(statusVista);

        }


    }

    private void actualizacionMedida(String statusVista) {
        HashMap<String, Object> hashMapResponseVista = new HashMap<>();
        if (statusVista.trim().isEmpty() || statusVista.trim().equals("") || statusVista.trim() == null) {
            hashMapResponseVista.put("vistaRespError", "Tipo medida no tiene descripcion unidad");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            Timber.d(" if (statusVista.trim()");

        } else {
            try {
                int stockXunidad = Integer.parseInt(onSpinnerTipoMedida.getMedidaDescripcion());
                int stockCaja = Integer.parseInt(stockPorCaja);
                int unidadtotales = stockCaja * stockXunidad;
                String resultadoTotal = unidadtotales + " / " + onSpinnerTipoMedida.getMedidaDescripcion();
                hashMapResponseVista.put("vistaResp", resultadoTotal);
                mutableLiveDataVista.postValue(hashMapResponseVista);
                Timber.d(" else (statusVista.trim()");
            } catch (Exception e) {
                hashMapResponseVista.put("vistaRespNull", null);
                mutableLiveDataVista.postValue(hashMapResponseVista);
            }

        }
    }

    void onRegistraProducto(String nombreProducto, String descripcionProducto, String minimoProducto, String codigoProducto, String loteCodigo, String stockPorCajas, String entradaPrecio, String salidaPrecio) {
        HashMap<String, Object> hashMapResponseVista = new HashMap<>();
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Nombre Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (descripcionProducto == null || descripcionProducto.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Descripcion Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (minimoProducto == null || minimoProducto.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Stock Minimo");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (stockPorCajas == null || stockPorCajas.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Stock Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (codigoProducto == null || codigoProducto.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Codigo del Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (loteCodigo == null || loteCodigo.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Lote del Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (entradaPrecio == null || entradaPrecio.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Precio de Entrada");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (salidaPrecio == null || salidaPrecio.isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Precio de Salida ");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (uriComprimida == null || uriComprimida.getPath().isEmpty() || uriComprimida.getPath().length() == 0) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Imagen Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }

        if (onSpinnerTipoColor == null || onSpinnerTipoColor.getIdModel().isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Color del Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (onSpinnerTipoMedida == null || onSpinnerTipoMedida.getIdModel().isEmpty()) {
            hashMapResponseVista.put("vistaRespError", "Ingrese Medida Producto");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }

        if (onSpinnerTipoAlmacen == null || onSpinnerTipoAlmacen.getIdModel().isEmpty() || onSpinnerTipoAlmacen.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Almacen");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (onSpinnerTipoMaterial == null || onSpinnerTipoMaterial.getIdModel().isEmpty() || onSpinnerTipoMaterial.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Material");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (onSpinnerTipoSuperficie == null || onSpinnerTipoSuperficie.getIdModel().isEmpty() || onSpinnerTipoSuperficie.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Superficie");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (onSpinnerTipoMarca == null || onSpinnerTipoMarca.getIdModel().isEmpty() || onSpinnerTipoMarca.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Marca");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }

        if (onSpinnerTipoCategoria == null || onSpinnerTipoCategoria.getIdModel().isEmpty() || onSpinnerTipoCategoria.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Categoria");
            mutableLiveDataVista.postValue(hashMapResponseVista);
            return;
        }
        if (onSpinnerTipoTipologia == null || onSpinnerTipoTipologia.getIdModel().isEmpty() || onSpinnerTipoTipologia.getIdModel().equals("0")) {
            hashMapResponseVista.put("vistaRespError", "Seleccione Tipologia");
            mutableLiveDataVista.postValue(hashMapResponseVista);
        }
        File file = new File(uriComprimida.getPath());
        RequestBody requestFile = RequestBody.create(MediaType.parse(Utilidades.getMimeType(file.getPath())), file);
        RequestBody requestNombreProducto = RequestBody.create(MediaType.parse("text/plain"), nombreProducto);
        RequestBody requestDescripcionProducto = RequestBody.create(MediaType.parse("text/plain"), descripcionProducto);
        RequestBody requestMinimoProducto = RequestBody.create(MediaType.parse("text/plain"), minimoProducto);
        RequestBody requestStockPorCaja = RequestBody.create(MediaType.parse("text/plain"), stockPorCajas);
        RequestBody requestCodigoProducto = RequestBody.create(MediaType.parse("text/plain"), codigoProducto);
        RequestBody requestLoteProducto = RequestBody.create(MediaType.parse("text/plain"), loteCodigo);
        RequestBody requestEntradaPrecio = RequestBody.create(MediaType.parse("text/plain"), entradaPrecio);
        RequestBody requestSalidaPrecio = RequestBody.create(MediaType.parse("text/plain"), salidaPrecio);
        RequestBody requestSelectedColor = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoColor.getIdModel());
        RequestBody requestSelectedMedida = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoMedida.getIdModel());
        RequestBody requestSelectedAlmacen = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoAlmacen.getIdModel());
        RequestBody requestSelectedMaterial = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoMaterial.getIdModel());
        RequestBody requestSelectedSuperficie = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoSuperficie.getIdModel());
        RequestBody requestSelectedMarca = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoMarca.getIdModel());
        RequestBody requestSelectedCategoria = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoCategoria.getIdModel());
        RequestBody requestSelectedTipologia = RequestBody.create(MediaType.parse("text/plain"), onSpinnerTipoTipologia.getIdModel());
        String tipoOperacion = "";
        String productoid = "";

        /*repository.initRegistroProducto(requestFile, requestNombreProducto, requestDescripcionProducto, requestMinimoProducto,
                requestStockPorCaja, requestCodigoProducto, requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen, requestSelectedMaterial, requestSelectedSuperficie,
                requestSelectedMarca, requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion, mutableLiveDataVista);
   */
        if (productoUi == null) {//Como es nulo entonces registramos producto
            tipoOperacion = "registrar";
            RequestBody requestTipoOperacion = RequestBody.create(MediaType.parse("text/plain"), tipoOperacion);
            RequestBody requestProductoId = RequestBody.create(MediaType.parse("text/plain"), productoid);
            repository.initRegistroProducto(requestFile, requestNombreProducto, requestDescripcionProducto, requestMinimoProducto,
                    requestStockPorCaja, requestCodigoProducto, requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                    requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen, requestSelectedMaterial, requestSelectedSuperficie,
                    requestSelectedMarca, requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion, requestProductoId, mutableLiveDataVista);
        } else {//actualizamos
            String[] parts = uriComprimida.getPath().split("/");
            String part2 = parts[1]; // 654321
            if (part2.equals("ferreteriaApi")) {
                tipoOperacion = "actualizarsinfoto";
            } else {
                tipoOperacion = "actualizarconfoto";
            }
            productoid = productoUi.getProducto_id();
            Timber.d("productoid : %s ", productoid);
            RequestBody requestProductoId = RequestBody.create(MediaType.parse("text/plain"), productoid);
            RequestBody requestTipoOperacion = RequestBody.create(MediaType.parse("text/plain"), tipoOperacion);

            repository.initRegistroProducto(requestFile, requestNombreProducto, requestDescripcionProducto, requestMinimoProducto,
                    requestStockPorCaja, requestCodigoProducto, requestLoteProducto, requestEntradaPrecio, requestSalidaPrecio,
                    requestSelectedColor, requestSelectedMedida, requestSelectedAlmacen, requestSelectedMaterial, requestSelectedSuperficie,
                    requestSelectedMarca, requestSelectedCategoria, requestSelectedTipologia, requestTipoOperacion, requestProductoId, mutableLiveDataVista);


        }

    }


    MutableLiveData<HashMap<String, String>> mutableLiveDataColorEdit = new MutableLiveData<>();


    void consultarColor(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getColor_color_id().equals(modelDefault.getIdModel())) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("keyValor", modelDefault.getNombreModel());
                    mutableLiveDataColorEdit.postValue(hashMap);
                }
            }
        } else {
            Timber.d("else : ");
        }
    }

    MutableLiveData<HashMap<String, String>> mutableLiveDataMedidaEdit = new MutableLiveData<>();

    void consultarMedida(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getMedida_medida_id().equals(modelDefault.getIdModel())) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("keyValor", modelDefault.getNombreModel());
                    mutableLiveDataMedidaEdit.postValue(hashMap);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataAlmacenEdit = new MutableLiveData<>();

    void consultarAlmacen(List<ModelDefault> modelDefaultList) {

        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getAlmacen_id().equals(modelDefault.getIdModel())) {
                    mutableLiveDataAlmacenEdit.postValue(i);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataMaterialEdit = new MutableLiveData<>();

    void consultarMaterial(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getMaterial_material_id().equals(modelDefault.getIdModel())) {
                    mutableLiveDataMaterialEdit.postValue(i);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataSuperficieEdit = new MutableLiveData<>();

    void consultarSuperficie(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getSuperficie_superficie_id().equals(modelDefault.getIdModel())) {
                    mutableLiveDataSuperficieEdit.postValue(i);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataTipologiaEdit = new MutableLiveData<>();

    void consultarTipologia(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getTipologia_id().equals(modelDefault.getIdModel())) {
                    mutableLiveDataTipologiaEdit.postValue(i);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataCategoriaEdit = new MutableLiveData<>();

    void consultarCategoria(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getCategoria_id().equals(modelDefault.getIdModel())) {
                    mutableLiveDataCategoriaEdit.postValue(i);
                }
            }
        }
    }

    MutableLiveData<Integer> mutableLiveDataMarcaEdit = new MutableLiveData<>();

    void consultarMarca(List<ModelDefault> modelDefaultList) {
        if (productoUi != null) {
            for (int i = 0; i < modelDefaultList.size(); i++) {
                ModelDefault modelDefault = modelDefaultList.get(i);
                if (productoUi.getProveedor_marca_id() != null
                        && productoUi.getProveedor_id() != null) {
                    if (productoUi.getProveedor_marca_id().equals(modelDefault.getIdModel())) {
                        mutableLiveDataMarcaEdit.postValue(i);
                    }
                }
                if (productoUi.getProveedor_marca_id() != null) {
                    if (productoUi.getProveedor_marca_id().equals(modelDefault.getIdModel())) {
                        mutableLiveDataMarcaEdit.postValue(i);
                    }
                }
                if (productoUi.getProveedor_id() != null) {
                    if (productoUi.getProveedor_id().equals(modelDefault.getIdModel())) {
                        mutableLiveDataMarcaEdit.postValue(i);
                    }
                }

            }
        }
    }

    MutableLiveData<Object> mutableLiveDataInicial = new MutableLiveData<>();

    public void initView() {
        if (productoUi != null) {

            mutableLiveDataInicial.postValue(productoUi);
            setDataEditar();
        }
    }

    private void setDataEditar() {
        uriComprimida = Uri.parse(productoUi.getProducto_image());

        ModelDefault modelDefaultColor = new ModelDefault();
        modelDefaultColor.setIdModel(productoUi.getColor_color_id());
        this.onSpinnerTipoColor = modelDefaultColor;

        ModelDefault modelDefaultMedida = new ModelDefault();
        modelDefaultMedida.setIdModel(productoUi.getMedida_medida_id());
        this.onSpinnerTipoMedida = modelDefaultMedida;

        ModelDefault modelDefaultAlmacen = new ModelDefault();
        modelDefaultAlmacen.setIdModel(productoUi.getAlmacen_id());
        this.onSpinnerTipoAlmacen = modelDefaultAlmacen;

        ModelDefault modelDefaultMaterial = new ModelDefault();
        modelDefaultMaterial.setIdModel(productoUi.getMaterial_material_id());
        this.onSpinnerTipoMaterial = modelDefaultMaterial;

        ModelDefault modelDefaultSuperficie = new ModelDefault();
        modelDefaultSuperficie.setIdModel(productoUi.getSuperficie_superficie_id());
        this.onSpinnerTipoSuperficie = modelDefaultSuperficie;

        ModelDefault modelDefaultMarca = new ModelDefault();
        modelDefaultMarca.setIdModel(productoUi.getProveedor_marca_id());
        this.onSpinnerTipoMarca = modelDefaultMarca;

        ModelDefault modelDefaultCategoria = new ModelDefault();
        modelDefaultCategoria.setIdModel(productoUi.getCategoria_id());
        this.onSpinnerTipoCategoria = modelDefaultCategoria;

        ModelDefault modelDefaultTipologia = new ModelDefault();
        modelDefaultTipologia.setIdModel(productoUi.getTipologia_id());
        this.onSpinnerTipoTipologia = modelDefaultTipologia;


    }

    public void refrescarCajas(String stockPorCajas) {
        this.stockPorCaja = stockPorCajas;
    }
}
