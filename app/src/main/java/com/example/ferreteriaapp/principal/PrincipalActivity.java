package com.example.ferreteriaapp.principal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.ferreteriaapp.Injection;
import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.PrincipalActivityBinding;
import com.example.ferreteriaapp.detalles.DetallesFragment;
import com.example.ferreteriaapp.operaciones.OperacionesActivity;
import com.example.ferreteriaapp.inventario.InventarioActivity;
import com.example.ferreteriaapp.model.Material;
import com.example.ferreteriaapp.operaciones.dialog.OperaDialog;
import com.example.ferreteriaapp.principal.fragment.ProductosFragment;
import com.example.ferreteriaapp.principal.listelos.ListelosFragment;
import com.example.ferreteriaapp.principal.service.PrincipalResponse;
import com.example.ferreteriaapp.principal.zocalos.ZocalosFragment;
import com.example.ferreteriaapp.productos.ListProductosActivity;
import com.example.ferreteriaapp.util.AppConstants;
import com.example.ferreteriaapp.util.MyFragmentAdapter;
import com.example.ferreteriaapp.util.SessionManager;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private PrincipalActivityBinding binding;
    private PrincipalViewModel viewModel;
    private SessionManager session;
    private ActionBarDrawerToggle toggle;
    private CursorAdapter adapter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.principal_activity);
        PrincipalFactory factory = Injection.providePrincipalViewModel(getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(PrincipalViewModel.class);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        initView();
        initViewModel();
        setDrawerState(false);
    }

    private void initView() {
        setSupportActionBar(binding.includeFab.toolbar);
        toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.includeFab.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.includeFab.fabDesloguear.setOnClickListener(v -> {
            session.logoutUser();
            finish();
        });
        binding.includeFab.fabRegistroProductos.setOnClickListener(v -> initStartRegistroProductos());
        binding.includeFab.fabItemInventario.setOnClickListener(v -> initStartInventario());
        binding.includeFab.fabItemEntrada.setOnClickListener(v -> initStartEntrada());


    }

    private void initStartEntrada() {
       /* OperaDialog enviarDiagnosticoDialog = new OperaDialog();
       // enviarDiagnosticoDialog.setCancelable(false);
        if (getFragmentManager() != null) {
            enviarDiagnosticoDialog.show(getSupportFragmentManager(), "ABC");
        }*/

        FragmentManager fm = getSupportFragmentManager();
        OperaDialog operaDialog = OperaDialog.newInstance("Some Title");
        operaDialog.show(fm, "fragment_edit_name");
    }

    private void initStartInventario() {
        Intent intent = new Intent(PrincipalActivity.this, InventarioActivity.class);
        startActivity(intent);
    }

    private void initStartRegistroProductos() {
        Intent intent = new Intent(PrincipalActivity.this, ListProductosActivity.class);
        startActivity(intent);
    }

    private void initViewModel() {
        viewModel.obtenerMaterialLista();
        viewModel.mutableLiveDataListaMaterial.observe(this, this::resultadoListadoMaterial);
        viewModel.mutableLiveDataListaBuscador.observe(this, this::resultadoBuscador);
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

    private void resultadoListadoMaterial(HashMap<String, Object> stringObjectHashMap) {
        List<Material> materialList = (List<Material>) stringObjectHashMap.get(AppConstants.RESPONSE_RETROFIT_FINALIZADO);
        if (materialList != null && materialList.size() > 0) {
            Timber.d("materialList : %s ", materialList.size());
            setupFragments(materialList);
            return;
        }

        viewModel.resultadoListadoMaterial(stringObjectHashMap);
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


    private void setupFragments(List<Material> materialList) {
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        for (Material c : materialList) {
            adapter.addFragment(ProductosFragment
                            .newInstance(c.getMaterial_nombre(),
                                    String.valueOf(c.getMaterial_id())),
                    c.getMaterial_nombre());
        }
        adapter.addFragment(ListelosFragment.newInstance("Listelos", "13"), "Listelos");
        adapter.addFragment(ZocalosFragment.newInstance("Zocalos", "12"), "Zocalos");
        binding.includeFab.viewpager.setAdapter(adapter);
        binding.includeFab.tabs.setupWithViewPager(binding.includeFab.viewpager);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Timber.d("nav_home");
                break;
            case R.id.nav_send:

                Timber.d("nav_send");
                break;
            default:
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(v -> onSupportNavigateUp());
            toggle.syncState();
        }
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
}
