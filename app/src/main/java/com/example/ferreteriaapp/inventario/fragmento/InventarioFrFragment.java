package com.example.ferreteriaapp.inventario.fragmento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.InventarioFragmentBinding;
import com.example.ferreteriaapp.detalles.DetallesFragment;
import com.example.ferreteriaapp.inventario.fragmento.adapter.InventarioFrAdapter;
import com.example.ferreteriaapp.inventario.fragmento.listener.InventarioListener;
import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrResponse;
import com.example.ferreteriaapp.util.AppConstants;
import com.gcssloop.widget.PagerConfig;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;


public class InventarioFrFragment extends Fragment implements PagerGridLayoutManager
        .PageListener, InventarioListener {

    public static final String ARG_TITLE = "arg_title";
    public static final String ARG_ALAMACEN_ID = "arg_almacen_id";
    public static final String ARG_ALMACEN = "arg_almacen";

    private InventarioFragmentBinding binding;
    private InventarioFrViewModel viewModel;
    private InventarioFrAdapter adapter;
    private PagerGridLayoutManager mLayoutManager;
    private int mRows = 2;
    private int mColumns = 2;

    public static InventarioFrFragment newInstance(String title, String almacenId) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_ALAMACEN_ID, almacenId);
        InventarioFrFragment fragment = new InventarioFrFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.inventario_fragment, container, false);
        InventarioFrFactory factory = Injection.provideListInventarioFr();
        viewModel = new ViewModelProvider(this, factory).get(InventarioFrViewModel.class);
        initViewModel();
        initAdapter();
        return binding.getRoot();
    }

    private void initAdapter() {
        mLayoutManager = new PagerGridLayoutManager(mRows, mColumns, PagerGridLayoutManager
                .VERTICAL);
        mLayoutManager.setPageListener(this);
        binding.reciclador.setLayoutManager(mLayoutManager);
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(binding.reciclador);
        PagerConfig.setShowLog(true);
        //init Adapter
        adapter = new InventarioFrAdapter(new ArrayList<>(),this);

        binding.reciclador.setAdapter(adapter);
    }

    private void initViewModel() {
        String alamcenId = getArguments().getString(ARG_ALAMACEN_ID);
        Timber.d("alamcenId : %s ", alamcenId);
        if (alamcenId == null) return;
        viewModel.obtenerListaAlmacen(alamcenId);
        viewModel.mutableLiveDataResponseLista.observe(this, this::initMostrarLista);
    }

    private void initMostrarLista(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            InventarioFrResponse listProductosResponseList = (InventarioFrResponse) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
            if (listProductosResponseList.getListaProductos() == null) {
                binding.progressBar.setVisibility(View.GONE);
                return;
            }
            String tipoOperacion = (String) stringObjectHashMap.get("tipoOperacion");
            if (tipoOperacion != null) {
                validarTipoOperacion(tipoOperacion, listProductosResponseList);
            }
        }
    }

    private void validarTipoOperacion(String tipoOperacion, InventarioFrResponse listProductosResponseList) {
        switch (tipoOperacion) {
            case "lista":
                adapter.setMostrarLista(listProductosResponseList.getListaProductos());
                binding.progressBar.setVisibility(View.GONE);
                break;
            case "loadMore":
                adapter.actualizarLista(listProductosResponseList.getListaProductos());
                binding.progressBar.setVisibility(View.GONE);
                break;
            default:
                Timber.d("default");
                break;
        }
    }


    @Override
    public void onPageSizeChanged(int pageSize) {
        viewModel.paginaTotal(pageSize);
    }

    @Override
    public void onPageSelect(int pageIndex, int pageLastIndex) {
        viewModel.cargarData(pageIndex, pageIndex);
    }

    @Override
    public void itemClickInventario(InventarioFrResponse.ClassProductos productos) {
        Timber.d("onItemListelos : %s ", productos.getProducto_nombre());
        DetallesFragment fragment = new DetallesFragment();
        Bundle args = new Bundle();
        args.putString("producto_image", productos.getProducto_image());
        args.putString("producto_nombre", productos.getProducto_nombre());
        args.putString("producto_descripcion", productos.getProducto_descripcion());
        args.putString("producto_minimo", productos.getProducto_minimo());
        args.putString("producto_precio_in", productos.getProducto_precio_in());
        args.putString("producto_precio_out", productos.getProducto_precio_out());
        args.putString("producto_cod_producto", productos.getProducto_cod_producto());
        args.putString("producto_Stock", productos.getProducto_Stock());
        args.putString("color_nombre", productos.getColor_nombre());
        args.putString("categoria_abrev", productos.getCategoria_abrev());
        args.putString("tipologia_nombre", productos.getTipologia_nombre());
        args.putString("material_nombre", productos.getMaterial_nombre());
        args.putString("superficie_nombre", productos.getSuperficie_nombre());
        args.putString("producto_Lote", productos.getProducto_Lote());
        args.putString("almacen_nombre", productos.getAlmacen_nombre());
        fragment.setArguments(args);

        if (getFragmentManager() != null) {
            fragment.show(getFragmentManager(), "ABC");
        }
        Timber.d("onItemClickFragment : %s ", productos.getProducto_nombre());
    }
}
