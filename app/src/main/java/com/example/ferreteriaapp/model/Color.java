package com.example.ferreteriaapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "color")
public class Color {
    @PrimaryKey
    @ColumnInfo(name = "color_id")
    private int color_id;

    @ColumnInfo(name = "color_nombre")
    private String color_nombre;

    public Color() {
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_nombre() {
        return color_nombre;
    }

    public void setColor_nombre(String color_nombre) {
        this.color_nombre = color_nombre;
    }
}
