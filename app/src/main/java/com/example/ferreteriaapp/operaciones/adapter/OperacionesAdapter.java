package com.example.ferreteriaapp.operaciones.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.ItemOperacionesBinding;
import com.example.ferreteriaapp.operaciones.adapter.holder.OperacionesHolder;
import com.example.ferreteriaapp.operaciones.model.OperacionesUi;

import java.util.List;

public class OperacionesAdapter extends RecyclerView.Adapter<OperacionesHolder> {

    private List<OperacionesUi> operacionesUiList;

    public OperacionesAdapter(List<OperacionesUi> operacionesUiList) {
        this.operacionesUiList = operacionesUiList;
    }

    @NonNull
    @Override
    public OperacionesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOperacionesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_operaciones, parent, false);
        return new OperacionesHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull OperacionesHolder holder, int position) {
        OperacionesUi operacionesUi = operacionesUiList.get(position);
        holder.bind(operacionesUi);
    }

    @Override
    public int getItemCount() {
        return operacionesUiList.size();
    }

    public void agregarOperaciones(OperacionesUi operacionesUi) {
        operacionesUiList.add(operacionesUi);
        notifyDataSetChanged();
    }

    public void limpiarData() {
        this.operacionesUiList.clear();
        notifyDataSetChanged();
    }
}
