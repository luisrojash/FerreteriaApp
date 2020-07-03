package com.example.ferreteriaapp;

import android.content.Context;

import com.example.ferreteriaapp.api.RetrofitInstance;
import com.example.ferreteriaapp.base.AppExecutors;
import com.example.ferreteriaapp.db.FerreDB;
import com.example.ferreteriaapp.inventario.service.InventarioService;
import com.example.ferreteriaapp.operaciones.OperacionesFactory;
import com.example.ferreteriaapp.operaciones.OperacionesRepository;
import com.example.ferreteriaapp.operaciones.service.OperacionesService;
import com.example.ferreteriaapp.inventario.InventarioFactory;
import com.example.ferreteriaapp.inventario.InventarioRepository;
import com.example.ferreteriaapp.inventario.fragmento.InventarioFrFactory;
import com.example.ferreteriaapp.inventario.fragmento.InventarioFrRepository;
import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrService;
import com.example.ferreteriaapp.login.LoginFactory;
import com.example.ferreteriaapp.login.LoginRepository;
import com.example.ferreteriaapp.login.service.LoginService;
import com.example.ferreteriaapp.principal.PrincipalFactory;
import com.example.ferreteriaapp.principal.PrincipalRepository;
import com.example.ferreteriaapp.principal.fragment.ProductosFactory;
import com.example.ferreteriaapp.principal.fragment.ProductosRepository;
import com.example.ferreteriaapp.principal.fragment.service.ProductosService;
import com.example.ferreteriaapp.principal.listelos.ListelosFactory;
import com.example.ferreteriaapp.principal.listelos.ListelosRepository;
import com.example.ferreteriaapp.principal.listelos.service.ListelosService;
import com.example.ferreteriaapp.principal.service.PrincipalService;
import com.example.ferreteriaapp.principal.zocalos.ZocalosFactory;
import com.example.ferreteriaapp.principal.zocalos.ZocalosRepository;
import com.example.ferreteriaapp.principal.zocalos.service.ZocalosService;
import com.example.ferreteriaapp.productos.ListProductosFactory;
import com.example.ferreteriaapp.productos.ListProductosRepository;
import com.example.ferreteriaapp.productos.registro.RegProductosFactory;
import com.example.ferreteriaapp.productos.registro.RegProductosRepository;
import com.example.ferreteriaapp.productos.registro.service.RegProductosService;
import com.example.ferreteriaapp.productos.service.ListProductosService;
import com.example.ferreteriaapp.registroUser.RegistroUserFactory;
import com.example.ferreteriaapp.registroUser.RegistroUserRepository;
import com.example.ferreteriaapp.registroUser.service.RegistroUserService;
import com.example.ferreteriaapp.splash.SplashFactory;
import com.example.ferreteriaapp.splash.SplashRepository;
import com.example.ferreteriaapp.splash.service.SplashService;
import com.example.ferreteriaapp.util.AppConstants;

public class Injection {

    private String baseUrlProduccion = AppConstants.BASE_URL_PRUEBA;
    private String baseUrlPrueba = AppConstants.BASE_URL_NEW;

    public static SplashFactory provideSplashViewModel(Context context) {
        return new SplashFactory(new SplashRepository(new AppExecutors(),
                RetrofitInstance.createService(SplashService.class,
                        AppConstants.BASE_URL_PRUEBA), FerreDB.getInstance(context)));
    }


    public static LoginFactory provideLoginViewModel() {
        return new LoginFactory(new LoginRepository(RetrofitInstance.createService(LoginService.class,
                AppConstants.BASE_URL_PRUEBA)));
    }

    public static RegistroUserFactory provideRegistroUserViewModel() {
        return new RegistroUserFactory(new RegistroUserRepository(RetrofitInstance.createService(RegistroUserService.class,
                AppConstants.BASE_URL_PRUEBA)));
    }

    public static PrincipalFactory providePrincipalViewModel(Context context) {
        return new PrincipalFactory(new PrincipalRepository(new AppExecutors(),
                RetrofitInstance.createService(PrincipalService.class, AppConstants.BASE_URL_PRUEBA),
                FerreDB.getInstance(context)));
    }

    public static RegProductosFactory provideRegistroViewModel(Context context) {
        return new RegProductosFactory(new RegProductosRepository(new AppExecutors(),
                RetrofitInstance.createService(RegProductosService.class, AppConstants.BASE_URL_PRUEBA),
                FerreDB.getInstance(context)), context);
    }

    public static ListProductosFactory providelistProductoslViewModel() {
        return new ListProductosFactory(new ListProductosRepository(RetrofitInstance.createService(ListProductosService.class, AppConstants.BASE_URL_PRUEBA)));
    }

    public static ListelosFactory provideListListelosViewModel() {
        return new ListelosFactory(new ListelosRepository(RetrofitInstance.createService(ListelosService.class, AppConstants.BASE_URL_PRUEBA)));
    }

    public static ZocalosFactory provideListZocalosViewModel() {
        return new ZocalosFactory(new ZocalosRepository(RetrofitInstance.createService(ZocalosService.class, AppConstants.BASE_URL_PRUEBA)));
    }

    public static ProductosFactory provideListProductosFViewModel() {
        return new ProductosFactory(new ProductosRepository(RetrofitInstance.createService(ProductosService.class, AppConstants.BASE_URL_PRUEBA)));
    }

    public static InventarioFactory proveListFactory(Context context) {
        return new InventarioFactory(new InventarioRepository(FerreDB.getInstance(context), new AppExecutors(),
                RetrofitInstance.createService(InventarioService.class,AppConstants.BASE_URL_PRUEBA)));
    }

    public static InventarioFrFactory provideListInventarioFr() {
        return new InventarioFrFactory(new InventarioFrRepository(RetrofitInstance.createService(InventarioFrService.class, AppConstants.BASE_URL_PRUEBA)));
    }

    public static OperacionesFactory provideEntrada(Context context) {
        return new OperacionesFactory(new OperacionesRepository(RetrofitInstance.createService(OperacionesService.class, AppConstants.BASE_URL_PRUEBA), FerreDB.getInstance(context),new AppExecutors()));
    }


}
