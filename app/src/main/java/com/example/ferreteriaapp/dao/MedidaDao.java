package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Medida;


import java.util.List;

@Dao
public interface MedidaDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Medida> medidaList);
    /*Lista de Categorias*/
    @Query("SELECT * FROM medida ")
    List<Medida> obtenerListaMedida();
}
