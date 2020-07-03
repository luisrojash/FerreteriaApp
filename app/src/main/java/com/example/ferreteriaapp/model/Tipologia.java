package com.example.ferreteriaapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipologia")
public class Tipologia {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tipologia_id")
    private int tipologia_id;

    @ColumnInfo(name = "tipologia_nombre")
    private String tipologia_nombre;

    public int getTipologia_id() {
        return tipologia_id;
    }

    public void setTipologia_id(int tipologia_id) {
        this.tipologia_id = tipologia_id;
    }

    public String getTipologia_nombre() {
        return tipologia_nombre;
    }

    public void setTipologia_nombre(String tipologia_nombre) {
        this.tipologia_nombre = tipologia_nombre;
    }
}
