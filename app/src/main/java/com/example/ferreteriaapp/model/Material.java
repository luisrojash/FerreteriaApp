package com.example.ferreteriaapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "material")
public class Material {
    @PrimaryKey
    @ColumnInfo(name = "material_id")
    private int material_id;

    @ColumnInfo(name = "material_nombre")
    private String material_nombre;

    public Material() {
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_nombre() {
        return material_nombre;
    }

    public void setMaterial_nombre(String material_nombre) {
        this.material_nombre = material_nombre;
    }
}
