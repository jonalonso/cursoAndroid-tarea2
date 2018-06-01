package com.stvansolano.cursoandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.stvansolano.cursoandroid.R;
import com.stvansolano.cursoandroid.modelos.Usuario;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class UsuarioAdapter extends ArrayAdapter {
    private ArrayList<Usuario> items;
    private int resourceId;

    public UsuarioAdapter(Context context, int resourceId, ArrayList<Usuario> items)
    {
        super(context, resourceId, items);

        this.resourceId = resourceId;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //TextView textView = ((Activity)getContext()).findViewById(R.id.textoNombre);
        //textView.setText(items.get(position).getNombre());
        //return convertView;
        try{
            Usuario usuario = (Usuario) getItem(position);
            String nombre = usuario.getNombre() + "(" + usuario.getTelefono() + ")";
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(this.resourceId,parent,false);
            }
            TextView textoNombre = convertView.findViewById(R.id.textoNombre);
            textoNombre.setText(nombre);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return convertView;
    }

    

    public ArrayList<Usuario> getItems(){
        return items;
    }
}
