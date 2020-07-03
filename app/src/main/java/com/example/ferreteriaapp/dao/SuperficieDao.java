package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Superficie;


import java.util.List;

@Dao
public interface SuperficieDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Superficie> superficieList);
    /*Lista de Categorias*/
    @Query("SELECT * FROM superficie ")
    List<Superficie> obtenerListaSuperficie();
}
