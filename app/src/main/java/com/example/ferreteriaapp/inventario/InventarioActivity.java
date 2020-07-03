package com.example.ferreteriaapp.inventario;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.InventarioActivityBinding;
import com.example.ferreteriaapp.detalles.DetallesFragment;
import com.example.ferreteriaapp.inventario.fragmento.InventarioFrFragment;
import com.example.ferreteriaapp.model.Almacen;
import com.example.ferreteriaapp.principal.service.PrincipalResponse;
import com.example.ferreteriaapp.util.MyFragmentAdapter;

import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class InventarioActivity extends AppCompatActivity {
    InventarioActivityBinding binding;
    InventarioViewModel viewModel;
    private CursorAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.inventario_activity);
        InventarioFactory factory = Injection.proveListFactory(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(InventarioViewModel.class);
       // binding.toolbar.setTitle("Inventario");
        getSupportActionBar().setTitle("Inventario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViewModel();
    }

    private void initViewModel() {
        viewModel.obtenerListaInventario();
        viewModel.mutableLiveDataResponseLista.observe(this, this::listaAlmcen);
        viewModel.mutableLiveDataListaBuscador.observe(this,this::resultadoBuscador);
    }

    private void listaAlmcen(HashMap<String, Object> stringObjectHashMap) {

        if (stringObjectHashMap.get("listaAlmacen") != null) {
            List<Almacen> listaAlmacen = (List<Almacen>) stringObjectHashMap.get("listaAlmacen");
            setupFragments(listaAlmacen);
        }
    }

    private void setupFragments(List<Almacen> listaAlmacen) {
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

       /* adapter.addFragment(ActualFragment.newInstance("Actual"), "Actual");
        adapter.addFragment(MensualFragment.newInstance("Mensual"), "Mensual");*/
        for (Almacen c : listaAlmacen) {
            Timber.d("alamcenId : %s ", c.getAlmacen_id());
            adapter.addFragment(InventarioFrFragment.newInstance(c.getAlmacen_nombre(),
                    c.getAlmacen_id()),
                    c.getAlmacen_nombre());
        }
        binding.viewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewpager);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Timber.d("nav_home");
                break;
            default:
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscador, menu);

        Activity activity = this;
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Buscando productos...");
        String[] columNames = {SearchManager.SUGGEST_COLUMN_TEXT_1};
        int[] viewIds = {android.R.id.text1};
        adapter = new SimpleCursorAdapter(this,
                R.layout.item_buscador, null, columNames, viewIds);

        searchView.setSuggestionsAdapter(adapter);
        searchView.setOnQueryTextListener(getOnQueryTextListener(activity));
        searchView.setOnSuggestionListener(getOnSuggestionClickListener());

        return true;
    }

    private SearchView.OnSuggestionListener getOnSuggestionClickListener() {
        return new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int index) {
                PrincipalResponse.ProductosResp productos
                        = productosRespList.get(index);
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
                    fragment.show(getSupportFragmentManager(), "ABC");
                }
                return true;
            }
        };
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener(Activity activity) {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() < 2) {
                    return false;
                }

                viewModel.buscarProducto(s);
                return true;
            }
        };
    }

    private void resultadoBuscador(HashMap<String, Object> stringObjectHashMap) {
        PrincipalResponse service = (PrincipalResponse) stringObjectHashMap.get("service");
        if (service != null) {
            initValidarTry(service);
        }
    }

    private void initValidarTry(PrincipalResponse service) {
        try {
            Cursor cursor = createCursorFromResult(service.getListaProductos());
            adapter.swapCursor(cursor);
        } catch (Exception e) {
            Timber.d("Exeption !!");
        }
    }

    private List<PrincipalResponse.ProductosResp> productosRespList;

    private Cursor createCursorFromResult(List<PrincipalResponse.ProductosResp> productosRespList) {

        this.productosRespList = productosRespList;
        String[] menuCols = new String[]{BaseColumns._ID,
                SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA};

        MatrixCursor cursor = new MatrixCursor(menuCols);
        int counter = 0;
        for (PrincipalResponse.ProductosResp productosResp
                : productosRespList) {
            counter++;
            cursor.addRow(new Object[]{counter, productosResp.getProducto_nombre(), productosResp.getProducto_id()});
        }
        return cursor;
    }

}
