package com.example.ferreteriaapp.operaciones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.operaciones.model.ModelDefault;


import java.util.List;

public class SpinnerDefault extends ArrayAdapter<ModelDefault> {

    private final LayoutInflater inflater;
    private final Context context;
    private final List<ModelDefault> modelDefaultList;
    private final int resource;

    public SpinnerDefault(@NonNull Context context, int resource, @NonNull List<ModelDefault> modelDefaultList) {
        super(context, resource, modelDefaultList);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.modelDefaultList = modelDefaultList;
        this.resource = resource;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(resource, parent, false);
        TextView textView = view.findViewById(R.id.textViewSpinnerTipoDocumento);
        textView.setText(modelDefaultList.get(position).getNombreModel());
        return view;
    }
}
