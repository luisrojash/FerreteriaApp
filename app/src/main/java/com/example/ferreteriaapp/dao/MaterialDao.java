package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Material;


import java.util.List;

@Dao
public interface MaterialDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Material> materialList);
    /*Lista de Categorias*/
    @Query("SELECT * FROM material ")
    List<Material> obtenerListaMaterial();
}
