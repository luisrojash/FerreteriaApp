package com.example.ferreteriaapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ferreteriaapp.model.Proveedor;


import java.util.List;

@Dao
public interface ProveedorDao {

    /*Insertacion Tablas Maestras*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Proveedor> proveedorList);
    /*obteniendo Proveedor */
    @Query("select * from provedor where proveedor_id = :proveedorId")
    Proveedor obtenerProveedor(String proveedorId);
    /*Lista de Marca*/
    @Query("SELECT * FROM provedor ")
    List<Proveedor> obtenerListaMarca();

}
