package com.example.ferreteriaapp.productos.registro.service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegProductosService {

    @Multipart
    @POST("apiNew/RegistroProductoApi.php")
    Call<RegProductosResponse> registrarProducto(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                                 @Part("requestTipoOperacion") RequestBody requestTipoOperacion,
                                                 @Part("requestNombreProducto") RequestBody requestNombreProducto,
                                                 @Part("requestDescripcionProducto") RequestBody requestDescripcionProducto,
                                                 @Part("requestMinimoProducto") RequestBody requestMinimoProducto,
                                                 @Part("requestStockPorCaja") RequestBody requestStockPorCaja,
                                                 @Part("requestCodigoProducto") RequestBody requestCodigoProducto,
                                                 @Part("requestLoteProducto") RequestBody requestLoteProducto,
                                                 @Part("requestEntradaPrecio") RequestBody requestEntradaPrecio,
                                                 @Part("requestSalidaPrecio") RequestBody requestSalidaPrecio,
                                                 @Part("requestSelectedColor") RequestBody requestSelectedColor,
                                                 @Part("requestSelectedMedida") RequestBody requestSelectedMedida,
                                                 @Part("requestSelectedAlmacen") RequestBody requestSelectedAlmacen,
                                                 @Part("requestSelectedMaterial") RequestBody requestSelectedMaterial,
                                                 @Part("requestSelectedSuperficie") RequestBody requestSelectedSuperficie,
                                                 @Part("requestSelectedMarca") RequestBody requestSelectedMarca,
                                                 @Part("requestSelectedCategoria") RequestBody requestSelectedCategoria,
                                                 @Part("requestSelectedTipologia") RequestBody requestSelectedTipologia);

    @Multipart
    @POST("apiNew/RegistroProductoApi.php")
    Call<RegProductosResponse> registrarSinFotoProducto(@Part("requestTipoOperacion") RequestBody requestTipoOperacion,
                                                        @Part("requestNombreProducto") RequestBody requestNombreProducto,
                                                        @Part("requestDescripcionProducto") RequestBody requestDescripcionProducto,
                                                        @Part("requestMinimoProducto") RequestBody requestMinimoProducto,
                                                        @Part("requestStockPorCaja") RequestBody requestStockPorCaja,
                                                        @Part("requestCodigoProducto") RequestBody requestCodigoProducto,
                                                        @Part("requestLoteProducto") RequestBody requestLoteProducto,
                                                        @Part("requestEntradaPrecio") RequestBody requestEntradaPrecio,
                                                        @Part("requestSalidaPrecio") RequestBody requestSalidaPrecio,
                                                        @Part("requestSelectedColor") RequestBody requestSelectedColor,
                                                        @Part("requestSelectedMedida") RequestBody requestSelectedMedida,
                                                        @Part("requestSelectedAlmacen") RequestBody requestSelectedAlmacen,
                                                        @Part("requestSelectedMaterial") RequestBody requestSelectedMaterial,
                                                        @Part("requestSelectedSuperficie") RequestBody requestSelectedSuperficie,
                                                        @Part("requestSelectedMarca") RequestBody requestSelectedMarca,
                                                        @Part("requestSelectedCategoria") RequestBody requestSelectedCategoria,
                                                        @Part("requestSelectedTipologia") RequestBody requestSelectedTipologia,
                                                        @Part("requestProductoId") RequestBody requestProductoId);

    @Multipart
    @POST("apiNew/RegistroProductoApi.php")
    Call<RegProductosResponse> registrarConFotoProducto(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                                        @Part("requestTipoOperacion") RequestBody requestTipoOperacion,
                                                        @Part("requestNombreProducto") RequestBody requestNombreProducto,
                                                        @Part("requestDescripcionProducto") RequestBody requestDescripcionProducto,
                                                        @Part("requestMinimoProducto") RequestBody requestMinimoProducto,
                                                        @Part("requestStockPorCaja") RequestBody requestStockPorCaja,
                                                        @Part("requestCodigoProducto") RequestBody requestCodigoProducto,
                                                        @Part("requestLoteProducto") RequestBody requestLoteProducto,
                                                        @Part("requestEntradaPrecio") RequestBody requestEntradaPrecio,
                                                        @Part("requestSalidaPrecio") RequestBody requestSalidaPrecio,
                                                        @Part("requestSelectedColor") RequestBody requestSelectedColor,
                                                        @Part("requestSelectedMedida") RequestBody requestSelectedMedida,
                                                        @Part("requestSelectedAlmacen") RequestBody requestSelectedAlmacen,
                                                        @Part("requestSelectedMaterial") RequestBody requestSelectedMaterial,
                                                        @Part("requestSelectedSuperficie") RequestBody requestSelectedSuperficie,
                                                        @Part("requestSelectedMarca") RequestBody requestSelectedMarca,
                                                        @Part("requestSelectedCategoria") RequestBody requestSelectedCategoria,
                                                        @Part("requestSelectedTipologia") RequestBody requestSelectedTipologia,
                                                        @Part("requestProductoId") RequestBody requestProductoId);



}
