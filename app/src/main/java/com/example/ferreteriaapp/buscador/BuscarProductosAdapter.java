package com.example.ferreteriaapp.buscador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.buscador.modelo.BuscarProductosUi;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class BuscarProductosAdapter extends ArrayAdapter<BuscarProductosUi> {

    List<BuscarProductosUi> customers, tempCustomer, suggestions;

    public BuscarProductosAdapter(Context context, List<BuscarProductosUi> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.tempCustomer = new ArrayList<BuscarProductosUi>(objects);
        this.suggestions = new ArrayList<BuscarProductosUi>(objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // final BuscarProductosUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_default, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            try {
                txtCustomer.setText(customers.get(position).getNombreProductos());
            } catch (Exception e) {
                Timber.d("Exception");
            }
        return convertView;
    }

    @Override
    public BuscarProductosUi getItem(int position) {
        if (customers.size() == 0) return null;
        return customers.get(position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            BuscarProductosUi customer = (BuscarProductosUi) resultValue;
            return customer.getNombreProductos();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String textoChar = constraint.toString();
            String autoAyuda = "*";
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                suggestions.clear();
                for (BuscarProductosUi modelDefault : tempCustomer) {
                    try {
                        suggestions.add(modelDefault);
                    } catch (Exception e) {
                        Timber.d("Exception");
                    }
                }
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<BuscarProductosUi> c = (ArrayList<BuscarProductosUi>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (BuscarProductosUi cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };


    public void actualizarLista(List<BuscarProductosUi> listaProductoResp) {
        this.customers.clear();
        this.tempCustomer.clear();
        this.suggestions.clear();
        customers.addAll(listaProductoResp);
        tempCustomer.addAll(listaProductoResp);
        suggestions.addAll(listaProductoResp);
        notifyDataSetChanged();
    }
}
