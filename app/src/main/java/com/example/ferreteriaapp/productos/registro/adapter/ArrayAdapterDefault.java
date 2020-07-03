package com.example.ferreteriaapp.productos.registro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.ferreteriaapp.R;
import com.example.ferreteriaapp.productos.registro.ui.ModelDefault;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterDefault extends ArrayAdapter<ModelDefault> {

    List<ModelDefault> customers, tempCustomer, suggestions;

    public ArrayAdapterDefault(Context context, List<ModelDefault> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.tempCustomer = new ArrayList<ModelDefault>(objects);
        this.suggestions = new ArrayList<ModelDefault>(objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelDefault customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_default, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);
        if (txtCustomer != null)
            txtCustomer.setText(customer.getNombreModel()+"");
        return convertView;
    }


    @Override
    public ModelDefault getItem(int position) {
        return customers.get(position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            ModelDefault customer = (ModelDefault) resultValue;
            return customer.getNombreModel();
        }


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (ModelDefault fruit: tempCustomer) {
                    if (fruit.getNombreModel().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<ModelDefault> c = (ArrayList<ModelDefault>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (ModelDefault cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
