package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.ferreteriaapp.model.Categoria;

import java.util.List;

@Dao
public interface CategoriaDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Categoria> categoria);
    /*Lista de Categorias*/
    @Query("SELECT * FROM categoria ")
    List<Categoria> obtenerListaCategoria();

}
