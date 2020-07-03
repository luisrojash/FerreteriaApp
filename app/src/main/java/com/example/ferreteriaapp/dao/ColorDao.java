package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Color;


import java.util.List;

@Dao
public interface ColorDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Color> colorList);
    /*Lista Colores*/
    @Query("SELECT * FROM color ")
    List<Color> obtenerListaColor();
}
