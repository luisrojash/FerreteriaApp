package com.example.ferreteriaapp.operaciones;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ferreteriaapp.buscador.modelo.BuscarProductosUi;
import com.example.ferreteriaapp.lote.modelo.LoteUi;
import com.example.ferreteriaapp.operaciones.model.ModelDefault;
import com.example.ferreteriaapp.operaciones.model.OperacionesUi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class OperacionesViewModel extends ViewModel {

    MutableLiveData<HashMap<String, Object>> responseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> responseView = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> responseFabAgregar = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> responseLote = new MutableLiveData<>();
    MutableLiveData<Integer> responsePosicion = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> responseProductoUi = new MutableLiveData<>();

    private OperacionesRepository repository;
    private BuscarProductosUi buscarProductosUi;
    private ModelDefault almacenUi;
    private List<ModelDefault> listaAlmacen;
    private String tipoOperacion;
    private List<OperacionesUi> operacionesUiList = new ArrayList<>();
    private LoteUi loteUi;
    private int cantidadTotal;

    OperacionesViewModel(OperacionesRepository repository) {
        this.repository = repository;

    }

    void buscarProductos(String toString) {
        repository.buscarProductos(toString, responseMutableLiveData);
    }

    void productoSelected(BuscarProductosUi buscarProductosUi) {
        this.buscarProductosUi = buscarProductosUi;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("buscarProductosUi", buscarProductosUi);
        responseProductoUi.postValue(hashMap);
        Timber.d("productoSelected : %s  ", buscarProductosUi.getProductoImagen());
        for (ModelDefault aDefault : listaAlmacen) {
            if (buscarProductosUi.getAlmacen_id().equals(aDefault.getIdAlamacen())) {
                int modePosicion = listaAlmacen.indexOf(aDefault);
                responsePosicion.postValue(modePosicion);
                return;
            }
        }
    }

    public void obtenerListaAlmacen() {
        repository.obtenerListaAlmacen(responseView);
    }

    void listaAlmacen(List<ModelDefault> obtenerAlmacenLista) {
        this.listaAlmacen = obtenerAlmacenLista;
    }

    void almacenSelected(ModelDefault almacen) {
        this.almacenUi = almacen;
        if (buscarProductosUi == null) {
            return;
        }
        if (almacenUi == null) {
            return;
        }
        // repository.obtenerLote(buscarProductosUi.getIdProductos(),almacen.getIdAlamacen(),responseLote);
    }

    void fabAgregarItem(String lote, String cantidad, String cantidaUnidad) {
        if(tipoOperacion.equals("Entrada")){
            if (tipoStatus == null) {
                Timber.d("Eliga el tipo de Opcion");
                return;
            }
        }
        /*if (tipoStatus == null) {
            Timber.d("Eliga el tipo de Opcion");
            return;
        }*/
        String productoCantidadUnidad = "";
        if (cantidaUnidad == null || cantidaUnidad.isEmpty()) {
            productoCantidadUnidad = "0";
        } else {
            productoCantidadUnidad = cantidaUnidad;
        }


        Timber.d("fabAgregarItem ");
        if (buscarProductosUi == null) {
            return;
        }
        if (almacenUi == null) {
            return;
        }
        if (lote == null) {
            Timber.d("Ingrese Lote");
            return;
        }

        if (cantidad == null || cantidad.isEmpty()) {
            Timber.d("Ingrese cantidad");
            return;
        }
        if (operacionesUiList == null) return;

        if (operacionesUiList.size() > 0) {
            Timber.d("Maximo de Items 1 ");
            return;
        }
        Timber.d("initValidarTipoOperacion ");
        initValidarTipoOperacion(cantidad, lote, tipoOperacion, productoCantidadUnidad);
    }


    private int totalProductoUnidadEntrante = 0;

    int count = 0;


    private void initOperacionEntrada(int cantidadProductoUnidad, int cantidadEntrante, int medidaDescripcion,
                                      String cantidad, String lote, String tipoOperacion, String productoCantidadUnidad) {
        if (count == 0) {
            count++;
            Timber.d("cantidadProductoUnidad : %s ", cantidadProductoUnidad);
            Timber.d("cantidadEntrante : %s ", cantidadEntrante);
            Timber.d("totalProductoUnidadEntrante : %s ", cantidadProductoUnidad);

            totalProductoUnidadEntrante = cantidadProductoUnidad + cantidadEntrante; // 23 // 63
            Timber.d("totalProductoUnidadEntrantetotalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);
            if (totalProductoUnidadEntrante >= medidaDescripcion) {

                this.cantidadTotal = cantidadTotal + 1;
                totalProductoUnidadEntrante = totalProductoUnidadEntrante - medidaDescripcion;

                        /*Timber.d("cantidadTotal : %s ", cantidadTotal);
                        Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);*/
                initValidarTipoOperacion(cantidad, lote, tipoOperacion, productoCantidadUnidad);
                return;
                //productoUnidadEnviar = totalProductoUnidadEntrante - medidaDescripcion;//5
            } else {
                int total = cantidadProductoUnidad + totalProductoUnidadEntrante;
                Timber.d("Total : %s", total);
                if (total < medidaDescripcion) {

                }
                /*Si el Producto Unidad + StockUnidad es < medida descripcion se guarda*/
            }
        } else {
            Timber.d("totalProductoUnidadEntranteelse : %s ", totalProductoUnidadEntrante);
            if (totalProductoUnidadEntrante >= medidaDescripcion) {
                this.cantidadTotal = cantidadTotal + 1;
                totalProductoUnidadEntrante = totalProductoUnidadEntrante - medidaDescripcion;
                initValidarTipoOperacion(cantidad, lote, tipoOperacion, productoCantidadUnidad);
                return;
            }
        }
        Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);
    }

    int totalUnidadSuma;
    int totalproductoUnidad;

    private void initOperacionSalida(int cantidadProductoUnidad, int cantidadEntrante, int medidaDescripcion,
                                     String cantidad, String lote, String tipoOperacion, String productoCantidadUnidad) {
        if (count == 0) {
            count++;
            Timber.d("cantidadProductoUnidad : %s ", cantidadProductoUnidad);
            Timber.d("cantidadEntrante : %s ", cantidadEntrante);
            Timber.d("totalProductoUnidadEntrante : %s ", cantidadProductoUnidad);

            totalProductoUnidadEntrante = cantidadProductoUnidad - cantidadEntrante; // 23 // 63
            Timber.d("totalProductoUnidadEntrantetotalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);
            if (totalProductoUnidadEntrante >= medidaDescripcion) {

                this.cantidadTotal = cantidadTotal - 1;
                totalProductoUnidadEntrante = totalProductoUnidadEntrante - medidaDescripcion;
                Timber.d("totalProductoUnidadEntranteIF : %s ", totalProductoUnidadEntrante);
                        /*Timber.d("cantidadTotal : %s ", cantidadTotal);
                        Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);*/
                initValidarTipoOperacion(cantidad, lote, tipoOperacion, productoCantidadUnidad);
                return;
                //productoUnidadEnviar = totalProductoUnidadEntrante - medidaDescripcion;//5
            } else {
                int total = cantidadProductoUnidad - totalProductoUnidadEntrante;
                Timber.d("Total : %s", total);
                if (total < medidaDescripcion) {

                }
                /*Si el Producto Unidad + StockUnidad es < medida descripcion se guarda*/
            }
        } else {
            Timber.d("totalProductoUnidadEntranteelse : %s ", totalProductoUnidadEntrante);
            if (totalProductoUnidadEntrante >= medidaDescripcion) {
                this.cantidadTotal = cantidadTotal - 1;
                totalProductoUnidadEntrante = totalProductoUnidadEntrante - medidaDescripcion;
                Timber.d("totalProductoUnidadEntranteESLSE: %s ", totalProductoUnidadEntrante);
                initValidarTipoOperacion(cantidad, lote, tipoOperacion, productoCantidadUnidad);
                return;
            }
        }
        Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);

    }

    private void validacionLote(String lote, String cantidad, String productoCantidadUnidad) {

        if (tipoStatus.equals("buttonNuevo")) {
            if (loteUi == null) {
                loteUi = new LoteUi();
                loteUi.setLoteCodigo(lote);
                this.cantidadTotal = Integer.parseInt(cantidad);
                Timber.d("lote no existe : %s ", lote);
                // initGuardarOperacionEntradaLoteNueva(cantidadTotal, productoCantidadUnidad);
                Timber.d("lote no existe");
                return;
            }

            loteUi.setLoteCodigo(lote);
            this.cantidadTotal = Integer.parseInt(cantidad);
            Timber.d("lote no existe : %s ", lote);
            // initGuardarOperacionEntradaLoteNueva(cantidadTotal, productoCantidadUnidad);
            Timber.d("lote no existe");
            return;

        }
        if (tipoStatus.equals("buttonEditar")) {

        }

    }

    private void initValidarTipoOperacion(String cantidad, String lote, String tipoOperacion, String productoCantidadUnidad) {
        switch (tipoOperacion) {
            case "Entrada":
                Timber.d("Entrada");
                // validacionLote(lote, cantidad, productoCantidadUnidad);
                if (tipoStatus.equals("buttonNuevo")) {
                    if (loteUi == null) {
                        loteUi = new LoteUi();
                        loteUi.setLoteCodigo(lote);
                        this.cantidadTotal = Integer.parseInt(cantidad);
                        Timber.d("lote no existe : %s ", lote);
                        initGuardarOperacionEntradaLoteNueva(cantidadTotal, productoCantidadUnidad);
                        Timber.d("lote no existe");
                        return;
                    }

                    loteUi.setLoteCodigo(lote);
                    this.cantidadTotal = Integer.parseInt(cantidad);
                    Timber.d("lote no existe : %s ", lote);
                    initGuardarOperacionEntradaLoteNueva(cantidadTotal, productoCantidadUnidad);
                    Timber.d("lote no existe");
                    return;

                }

                if (loteUi == null) {
                    Timber.d("Use la aplicacion de la manera correcta!");
                    return;
                }

               /* if (loteUi == null) {

                    loteUi = new LoteUi();
                    loteUi.setLoteCodigo(lote);
                    this.cantidadTotal = Integer.parseInt(cantidad);
                    initGuardarOperacionEntradaLoteNueva(cantidadTotal, productoCantidadUnidad);
                    Timber.d("lote no existe");
                    return;
                }*/
                int cantidadAumentar = Integer.parseInt(cantidad);
                int stockActual = Integer.parseInt(loteUi.getStockProducto());
                int total = stockActual + cantidadAumentar;
                //this.cantidadTotal = String.valueOf(total);

                int medidaDescripcion = Integer.parseInt(buscarProductosUi.getMedidadDescripcion());//18
                int cantidadProductoUnidad = Integer.parseInt(loteUi.getUnidadProducto());//3
                int cantidadEntrante = Integer.parseInt(productoCantidadUnidad); // 20 // 60
                Timber.d("cantidadProductoUnidad : %s ", cantidadProductoUnidad);
                Timber.d("cantidadEntrante : %s ", cantidadEntrante);

                initOperacionEntrada(cantidadProductoUnidad, cantidadEntrante, medidaDescripcion,
                        cantidad, lote, tipoOperacion, productoCantidadUnidad);
                Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);

                initGuardarOperacionEntrada(total + cantidadTotal, totalProductoUnidadEntrante);
                Timber.d("lote existe");
                break;
            case "Salida":
                if (loteUi == null) {
                    Timber.d("lote is Null");
                    return;
                }

                int productoCajas = Integer.parseInt(cantidad);
                int productoUnidad = Integer.parseInt(productoCantidadUnidad);
                int medidaDes = Integer.parseInt(buscarProductosUi.getMedidadDescripcion());
                int stockProducto = Integer.parseInt(loteUi.getStockProducto());
                int stockUnidad = Integer.parseInt(loteUi.getUnidadProducto());


                if (productoUnidad == 0) {
                    int totalStockProducto = stockProducto - productoCajas;
                    productoUnidad = stockUnidad;
                    initGuardarOperacionEntrada(Math.abs(totalStockProducto), productoUnidad);
                    return;
                }


                if (stockUnidad > 0) {
                    if (productoUnidad <= stockUnidad) {
                        int totalStockProducto = stockProducto - productoCajas;

                        int totalProductoUnidad = stockUnidad - productoUnidad;


                        initGuardarOperacionEntrada(Math.abs(totalStockProducto), totalProductoUnidad);
                        return;

                    }

                    if (count == 0) {
                        totalUnidadSuma = medidaDes + stockUnidad;
                        totalproductoUnidad = productoUnidad;

                        Timber.d("totalUnidadSuma : %s ", totalUnidadSuma);
                        Timber.d("totalproductoUnidad : %s ", totalproductoUnidad);
                        if (totalproductoUnidad > totalUnidadSuma) {
                            totalUnidadSuma = totalUnidadSuma + medidaDes;
                            count++;
                            initValidarTipoOperacion(cantidad, lote, tipoOperacion, String.valueOf(totalUnidadSuma));
                            return;
                        }
                    } else {
                        if (totalproductoUnidad > totalUnidadSuma) {
                            totalUnidadSuma = totalUnidadSuma + medidaDes;
                            count++;
                            initValidarTipoOperacion(cantidad, lote, tipoOperacion, String.valueOf(totalUnidadSuma));
                            return;
                        }
                    }
                    int totalStockProducto = stockProducto - productoCajas;
                    int totalconteoSuma = totalUnidadSuma - totalproductoUnidad;
                    int conteo = count + 1;
                    int totalesCajas = totalStockProducto - conteo;

                    initGuardarOperacionEntrada(Math.abs(totalesCajas), totalconteoSuma);

                    Timber.d("totalesCajastotalesCajas : %s ", totalesCajas);
                    Timber.d("totalUnidadSuma : %s ", totalUnidadSuma);
                    Timber.d("totalconteoSuma : %s ", totalconteoSuma);
                    Timber.d("totalproductoUnidad : %s ", totalproductoUnidad);
                    Timber.d("countCajas : %s ", count + 1);
                    return;
                }

                totalUnidadSuma = (stockUnidad + productoUnidad);

                if (totalUnidadSuma > medidaDes) {
                    count++;
                    totalUnidadSuma = totalUnidadSuma - medidaDes;
                    initValidarTipoOperacion(cantidad, lote, tipoOperacion, String.valueOf(totalUnidadSuma));
                    return;
                }
                if (totalUnidadSuma < medidaDes) {
                    count++;
                    totalUnidadSuma = totalUnidadSuma - medidaDes;
                }


                int totalStockProducto = stockProducto - productoCajas;

                Timber.d("productoCajas : %s ", productoCajas);
                Timber.d("stockProducto : %s ", stockProducto);
                Timber.d("totalcajasas : %s ", totalStockProducto);
                int totalesCajas = totalStockProducto - count;
                Timber.d("totalesCajastotalesCajas : %s ", totalesCajas);
                int totalUnidad = Math.abs(totalUnidadSuma);
                initGuardarOperacionEntrada(Math.abs(totalesCajas), totalUnidad);
                Timber.d("count : %s ", count);
                Timber.d("totalcajasas : %s ", Math.abs(totalesCajas));
                Timber.d("totalUnidadSuma : %s ", totalUnidad);

                break;
            case "Devoluciones":
                if (loteUi == null) {
                    Timber.d("Seleccione Lote Correcto");
                    return;
                }
                int cantidadAumentarDev = Integer.parseInt(cantidad);
                int stockActualDev = Integer.parseInt(loteUi.getStockProducto());
                int totalDev = stockActualDev + cantidadAumentarDev;
                //this.cantidadTotal = String.valueOf(total);

                int medidaDescripcionDev = Integer.parseInt(buscarProductosUi.getMedidadDescripcion());//18
                int cantidadProductoUnidadDev = Integer.parseInt(loteUi.getUnidadProducto());//3
                int cantidadEntranteDev = Integer.parseInt(productoCantidadUnidad); // 20 // 60
                Timber.d("cantidadProductoUnidad : %s ", cantidadProductoUnidadDev);
                Timber.d("cantidadEntrante : %s ", cantidadEntranteDev);

                initOperacionEntrada(cantidadProductoUnidadDev, cantidadEntranteDev, medidaDescripcionDev,
                        cantidad, lote, tipoOperacion, productoCantidadUnidad);
                Timber.d("totalProductoUnidadEntrante : %s ", totalProductoUnidadEntrante);

                initGuardarOperacionEntrada(totalDev + cantidadTotal, totalProductoUnidadEntrante);
                Timber.d("lote existe");

                break;
            default:
                Timber.d("default");
                break;
        }

    }


    private void initGuardarOperacionEntradaLoteNueva(int cantidadTotal, String productoCantidadUnidad) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Timber.d("cantidad : %s ", productoCantidadUnidad);
        OperacionesUi operacionesUi = new OperacionesUi();
        operacionesUi.setNombreProducto(buscarProductosUi.getNombreProductos());
        operacionesUi.setCantidadEntrante(String.valueOf(cantidadTotal));
        operacionesUi.setNombreAlmacen(almacenUi.getNombreModel());
        operacionesUi.setIdLote(loteUi.getLoteCodigo());
        operacionesUi.setAlmacen_id(almacenUi.getIdAlamacen());
        operacionesUi.setInventarioMinimo(buscarProductosUi.getInventarioMinimo());
        operacionesUi.setPrecioIn(buscarProductosUi.getPrecioIn());
        operacionesUi.setPrecioOut(buscarProductosUi.getPrecioOut());
        operacionesUi.setProductoUnidad(buscarProductosUi.getProductoUnidad());
        operacionesUi.setProductoCodigo(buscarProductosUi.getProductoCodigo());
        operacionesUi.setProductoStockInicial(buscarProductosUi.getProductoStockInicial());
        operacionesUi.setProveedorCodigo(buscarProductosUi.getProveedorCodigo());
        operacionesUi.setColorCodigo(buscarProductosUi.getColorCodigo());
        operacionesUi.setCantidadUnidadEntrante(productoCantidadUnidad);
        operacionesUi.setCategoriaCatogia(buscarProductosUi.getCategoriaCatogia());
        operacionesUi.setTipologia(buscarProductosUi.getTipologia());
        operacionesUi.setMedidadCodigo(buscarProductosUi.getMedidadCodigo());
        operacionesUi.setMaterialCodigo(buscarProductosUi.getMaterialCodigo());
        operacionesUi.setSuperficieCodigo(buscarProductosUi.getSuperficieCodigo());
        operacionesUi.setLoteCodigo(buscarProductosUi.getLoteCodigo());
        operacionesUi.setM2(buscarProductosUi.getM2());
        operacionesUi.setProductoImage(buscarProductosUi.getProductoImagen());
        operacionesUi.setDescripcionProducto(buscarProductosUi.getDescripcionProductos());
        operacionesUiList.add(operacionesUi);
        stringObjectHashMap.put("operaciones", operacionesUi);
        responseView.postValue(stringObjectHashMap);
        Timber.d("buscarProductosUi : %s ", buscarProductosUi.getIdProductos());
        Timber.d("almacenUi : %s ", almacenUi.getIdModel());
    }

    private void initGuardarOperacionEntrada(int cantidad, int totalProductoUnidadEntrante) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Timber.d("cantidad : %s ", cantidad);
        OperacionesUi operacionesUi = new OperacionesUi();
        operacionesUi.setNombreProducto(buscarProductosUi.getNombreProductos());
        operacionesUi.setCantidadEntrante(String.valueOf(cantidad));
        operacionesUi.setNombreAlmacen(almacenUi.getNombreModel());
        operacionesUi.setIdLote(loteUi.getLoteCodigo());
        operacionesUi.setAlmacen_id(almacenUi.getIdAlamacen());
        operacionesUi.setInventarioMinimo(buscarProductosUi.getInventarioMinimo());
        operacionesUi.setPrecioIn(buscarProductosUi.getPrecioIn());
        operacionesUi.setPrecioOut(buscarProductosUi.getPrecioOut());
        operacionesUi.setProductoUnidad(buscarProductosUi.getProductoUnidad());
        operacionesUi.setProductoCodigo(buscarProductosUi.getProductoCodigo());
        operacionesUi.setProductoStockInicial(buscarProductosUi.getProductoStockInicial());
        operacionesUi.setProveedorCodigo(buscarProductosUi.getProveedorCodigo());
        operacionesUi.setColorCodigo(buscarProductosUi.getColorCodigo());
        operacionesUi.setCategoriaCatogia(buscarProductosUi.getCategoriaCatogia());
        operacionesUi.setTipologia(buscarProductosUi.getTipologia());
        operacionesUi.setMedidadCodigo(buscarProductosUi.getMedidadCodigo());
        operacionesUi.setMaterialCodigo(buscarProductosUi.getMaterialCodigo());
        operacionesUi.setSuperficieCodigo(buscarProductosUi.getSuperficieCodigo());
        operacionesUi.setLoteCodigo(buscarProductosUi.getLoteCodigo());
        operacionesUi.setM2(buscarProductosUi.getM2());
        operacionesUi.setCantidadUnidadEntrante(String.valueOf(totalProductoUnidadEntrante));
        operacionesUi.setProductoImage(buscarProductosUi.getProductoImagen());
        operacionesUi.setDescripcionProducto(buscarProductosUi.getDescripcionProductos());
        operacionesUiList.add(operacionesUi);
        stringObjectHashMap.put("operaciones", operacionesUi);
        responseView.postValue(stringObjectHashMap);
        Timber.d("buscarProductosUi : %s ", buscarProductosUi.getIdProductos());
        Timber.d("almacenUi : %s ", almacenUi.getIdModel());
    }

    private void initGuardarOperacion(int cantidad) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Timber.d("cantidad : %s ", cantidad);
        OperacionesUi operacionesUi = new OperacionesUi();
        operacionesUi.setNombreProducto(buscarProductosUi.getNombreProductos());
        operacionesUi.setCantidadEntrante(String.valueOf(cantidad));
        operacionesUi.setNombreAlmacen(almacenUi.getNombreModel());
        operacionesUi.setIdLote(loteUi.getLoteCodigo());
        operacionesUi.setAlmacen_id(almacenUi.getIdAlamacen());
        operacionesUi.setInventarioMinimo(buscarProductosUi.getInventarioMinimo());
        operacionesUi.setPrecioIn(buscarProductosUi.getPrecioIn());
        operacionesUi.setPrecioOut(buscarProductosUi.getPrecioOut());
        operacionesUi.setProductoUnidad(buscarProductosUi.getProductoUnidad());
        operacionesUi.setProductoCodigo(buscarProductosUi.getProductoCodigo());
        operacionesUi.setProductoStockInicial(buscarProductosUi.getProductoStockInicial());
        operacionesUi.setProveedorCodigo(buscarProductosUi.getProveedorCodigo());
        operacionesUi.setColorCodigo(buscarProductosUi.getColorCodigo());
        operacionesUi.setCategoriaCatogia(buscarProductosUi.getCategoriaCatogia());
        operacionesUi.setTipologia(buscarProductosUi.getTipologia());
        operacionesUi.setMedidadCodigo(buscarProductosUi.getMedidadCodigo());
        operacionesUi.setMaterialCodigo(buscarProductosUi.getMaterialCodigo());
        operacionesUi.setSuperficieCodigo(buscarProductosUi.getSuperficieCodigo());
        operacionesUi.setLoteCodigo(buscarProductosUi.getLoteCodigo());
        operacionesUi.setM2(buscarProductosUi.getM2());
        operacionesUi.setProductoImage(buscarProductosUi.getProductoImagen());
        operacionesUi.setDescripcionProducto(buscarProductosUi.getDescripcionProductos());
        operacionesUiList.add(operacionesUi);
        stringObjectHashMap.put("operaciones", operacionesUi);
        responseView.postValue(stringObjectHashMap);
        Timber.d("buscarProductosUi : %s ", buscarProductosUi.getIdProductos());
        Timber.d("almacenUi : %s ", almacenUi.getIdModel());
    }

    public void bundle(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
        Timber.d("bundleTipoOperacion : %s ", tipoOperacion);
    }

    String jsonOperacion;

    void enviarOperacion() {
        if (operacionesUiList == null) return;
        Gson gson = new Gson();
        this.jsonOperacion = gson.toJson(operacionesUiList);
        repository.initGuardarListaOperacion(jsonOperacion, tipoOperacion, responseFabAgregar);

    }

    void buscarLote(String toString) {
        if (buscarProductosUi == null) {
            Timber.d("buscarProductosUiNULL");
            return;
        }
        if (almacenUi == null) {
            Timber.d("almacenUiNULL");
            return;
        }
        repository.obtenerLote(buscarProductosUi.getProductoCodigo(),
                almacenUi.getIdAlamacen(),
                toString,
                responseLote);
    }


    void loteSelected(LoteUi loteUi) {
        this.loteUi = loteUi;
    }

    void limpiarData() {
        this.buscarProductosUi = null;
        this.loteUi = null;
        this.jsonOperacion = null;
        this.operacionesUiList.clear();
    }

    private String tipoStatus;

    void initTipoEstado(String tipoStatus) {
        this.tipoStatus = tipoStatus;
        Timber.d("TipoEstado : %s", tipoStatus);
    }
}
