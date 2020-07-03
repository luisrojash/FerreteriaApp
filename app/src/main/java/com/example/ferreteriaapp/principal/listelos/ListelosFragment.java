package com.example.ferreteriaapp.principal.listelos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ListelosFragmentBinding;
import com.example.ferreteriaapp.detalles.DetallesFragment;
import com.example.ferreteriaapp.principal.listelos.adapter.ListelosAdapter;
import com.example.ferreteriaapp.principal.listelos.listener.ListelosListener;
import com.example.ferreteriaapp.principal.listelos.service.ListelosResponse;
import com.example.ferreteriaapp.util.AppConstants;
import com.gcssloop.widget.PagerConfig;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

public class ListelosFragment extends Fragment implements ListelosListener, PagerGridLayoutManager.PageListener {

    public static final String ARG_TITLE = "arg_title";
    public static final String ARG_CODIGO = "arg_codigo";


    private ListelosFragmentBinding binding;
    private PagerGridLayoutManager mLayoutManager;
    private ListelosViewModel viewModel;
    private ListelosAdapter adapter;

    public static ListelosFragment newInstance(String title, String codigo) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CODIGO, codigo);
        ListelosFragment fragment = new ListelosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.listelos_fragment, container, false);
        ListelosFactory factory = Injection.provideListListelosViewModel();
        viewModel = new ViewModelProvider(this, factory).get(ListelosViewModel.class);
        viewModel.obtenerBundle(getArguments());
        setupListener();
        initAdapter();
        return binding.getRoot();
    }

    private void initAdapter() {
        int mRows = 2;
        int mColumns = 2;
        PagerGridLayoutManager mLayoutManager = new PagerGridLayoutManager(mRows, mColumns, PagerGridLayoutManager.VERTICAL);
        mLayoutManager.setPageListener(this);
        binding.reciclador.setLayoutManager(mLayoutManager);
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(binding.reciclador);
        PagerConfig.setShowLog(true);
        adapter = new ListelosAdapter(new ArrayList<>(), this);
        binding.reciclador.setAdapter(adapter);
    }

    private void setupListener() {
        viewModel.mutableLiveDataResponseLista.observe(this, this::mostrarLista);
    }

    private void mostrarLista(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            ListelosResponse listProductosResponseList = (ListelosResponse) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
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

    private void validarTipoOperacion(String tipoOperacion, ListelosResponse listProductosResponseList) {
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
    public void onItemListelos(ListelosResponse.ClassProductos productos) {
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

    @Override
    public void onPageSizeChanged(int pageSize) {
        viewModel.paginaTotal(pageSize);
    }

    @Override
    public void onPageSelect(int pageIndex, int pageLastIndex) {
        viewModel.cargarData(pageIndex, pageIndex);
    }
}
