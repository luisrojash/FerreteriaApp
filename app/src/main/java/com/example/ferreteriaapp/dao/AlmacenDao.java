package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Almacen;


import java.util.List;

@Dao
public interface AlmacenDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Almacen> almacenList);

    /*Lista de Categorias*/
    @Query("SELECT * FROM almacen ")
    List<Almacen> obtenerListaAlmacen();

    @Query("SELECT * FROM almacen where almacen_id =:almacenId ")
    List<Almacen> obtenerAlmacen(String almacenId);
}
