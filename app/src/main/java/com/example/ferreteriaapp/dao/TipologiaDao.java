package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Tipologia;

import java.util.List;

@Dao
public interface TipologiaDao {
    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Tipologia> tipologiaList);

    /*Lista de Categorias*/
    @Query("SELECT * FROM tipologia ")
    List<Tipologia> obtenerListaTipologia();
}
