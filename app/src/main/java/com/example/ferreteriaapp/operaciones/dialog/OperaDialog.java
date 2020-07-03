package com.example.ferreteriaapp.operaciones.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.databinding.OperaDialogBinding;
import com.example.ferreteriaapp.operaciones.OperacionesActivity;

public class OperaDialog extends DialogFragment {

    private OperaDialogBinding binding;

    public OperaDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static OperaDialog newInstance(String title) {
        OperaDialog frag = new OperaDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.opera_dialog, container, false);
        // Set transparent background and no title
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        /*if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }*/

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        /*int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);*/
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOnClickStartActivity();
    }

    private void initOnClickStartActivity() {
        binding.textViewEntrada.setOnClickListener(v -> initStartActivityEntrada());
        binding.textViewSalida.setOnClickListener(v -> initStartActivitySalida());
        binding.devoluciones.setOnClickListener(v->initStartActivityDevoluciones());
    }

    private void initStartActivityDevoluciones() {
        String tipoOperacion = "Devoluciones";
        startActivityOperaciones(tipoOperacion);
    }

    private void initStartActivitySalida() {
        String tipoOperacion = "Salida";
        startActivityOperaciones(tipoOperacion);
    }

    private void initStartActivityEntrada() {
        String tipoOperacion = "Entrada";
        startActivityOperaciones(tipoOperacion);
    }

    private void startActivityOperaciones(String tipoOperacion) {
        Intent intent = new Intent(requireContext(), OperacionesActivity.class);
        intent.putExtra("tipoOperacion", tipoOperacion);
        startActivity(intent);
        dismiss();
    }

}
