package com.example.ferreteriaapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ferreteriaapp.dao.AlmacenDao;
import com.example.ferreteriaapp.dao.CategoriaDao;
import com.example.ferreteriaapp.dao.ColorDao;
import com.example.ferreteriaapp.dao.MaterialDao;
import com.example.ferreteriaapp.dao.MedidaDao;
import com.example.ferreteriaapp.dao.ProveedorDao;
import com.example.ferreteriaapp.dao.SuperficieDao;
import com.example.ferreteriaapp.dao.TipologiaDao;
import com.example.ferreteriaapp.model.Almacen;
import com.example.ferreteriaapp.model.Categoria;
import com.example.ferreteriaapp.model.Color;
import com.example.ferreteriaapp.model.Material;
import com.example.ferreteriaapp.model.Medida;
import com.example.ferreteriaapp.model.Proveedor;
import com.example.ferreteriaapp.model.Superficie;
import com.example.ferreteriaapp.model.Tipologia;


@Database(entities = {Categoria.class, Proveedor.class, Tipologia.class,
        Color.class, Material.class, Medida.class, Superficie.class,
        Almacen.class}, version = 1)
public abstract class FerreDB extends RoomDatabase {

    private static volatile FerreDB INSTANCE;

    public abstract CategoriaDao categoriaDao();

    public abstract ProveedorDao proveedorDao();

    public abstract TipologiaDao tipologiaDao();

    public abstract ColorDao colorDao();

    public abstract MaterialDao materialDao();

    public abstract MedidaDao medidaDao();

    public abstract SuperficieDao superficieDao();

    public abstract AlmacenDao almacenDao();

    public static FerreDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FerreDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FerreDB.class, "ferreDB.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
