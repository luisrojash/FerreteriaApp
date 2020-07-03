package com.example.ferreteriaapp.principal.fragment;

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
import com.example.ferreteriaapp.databinding.ProductosFragmentBinding;
import com.example.ferreteriaapp.detalles.DetallesFragment;
import com.example.ferreteriaapp.principal.fragment.adapter.ProductosAdapter;
import com.example.ferreteriaapp.principal.fragment.service.ProductosResponse;
import com.example.ferreteriaapp.principal.listener.FragmentListener;
import com.example.ferreteriaapp.util.AppConstants;
import com.gcssloop.widget.PagerConfig;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

import static com.example.ferreteriaapp.principal.listelos.ListelosFragment.ARG_CODIGO;

public class ProductosFragment extends Fragment implements PagerGridLayoutManager
        .PageListener, FragmentListener {

    public static final String ARG_TITLE = "arg_title";
    public static final String ARG_CATEGORY_ID = "arg_category_id";
    public static final String ARG_CATEGORY = "arg_category";

    private ProductosFragmentBinding binding;
    private PagerGridLayoutManager mLayoutManager;
    private int mRows = 2;
    private int mColumns = 2;
    private ProductosAdapter adapter;
    private ProductosViewModel viewModel;

    public static ProductosFragment newInstance(String title, String categoryId) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CODIGO, categoryId);
        ProductosFragment fragment = new ProductosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.productos_fragment, container, false);
        ProductosFactory factory = Injection.provideListProductosFViewModel();
        viewModel = new ViewModelProvider(this, factory).get(ProductosViewModel.class);
        viewModel.obtenerBundle(getArguments());
        setupListener();
        initAdapter();
        return binding.getRoot();
    }


    private void setupListener() {
        viewModel.mutableLiveDataResponseLista.observe(this, this::mostrarLista);
    }

    private void mostrarLista(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO) != null) {
            ProductosResponse listProductosResponseList = (ProductosResponse) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
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

    private void validarTipoOperacion(String tipoOperacion, ProductosResponse listProductosResponseList) {
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

    private void initAdapter() {
        mLayoutManager = new PagerGridLayoutManager(mRows, mColumns, PagerGridLayoutManager
                .VERTICAL);
        mLayoutManager.setPageListener(this);
        binding.reciclador.setLayoutManager(mLayoutManager);
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(binding.reciclador);

        PagerConfig.setShowLog(true);
        //init Adapter
        adapter = new ProductosAdapter(new ArrayList<>(), this);

        binding.reciclador.setAdapter(adapter);
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
    public void onItemClickFragment(ProductosResponse.ClassProductos productos) {
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
        //  fragment.setCancelable(false);
        if (getFragmentManager() != null) {
            fragment.show(getFragmentManager(), "ABC");
        }
        Timber.d("onItemClickFragment : %s ", productos.getProducto_nombre());
    }
}
