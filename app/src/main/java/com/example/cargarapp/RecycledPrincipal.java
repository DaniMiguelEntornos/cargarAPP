package com.example.cargarapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecycledPrincipal extends RecyclerView.Adapter<RecycledPrincipal.RecycledPrincipalResultHolder> {

        private List<String> data;

        public RecycledPrincipal(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public RecycledPrincipalResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_principal, parent, false);

            return new RecycledPrincipalResultHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycledPrincipalResultHolder holder, int position) {
            //holder.nombre.setText("MENU 1:");
           // holder.precio.setText("7.95");
            holder.nombre.setText(data.get(position));

        }

    @Override
    public int getItemCount() {return data.size();}


    class RecycledPrincipalResultHolder extends RecyclerView.ViewHolder{
            private TextView nombre;
            private TextView precio;

            public RecycledPrincipalResultHolder(@NonNull View itemView) {
                super(itemView);

                nombre = itemView.findViewById(R.id.nombre);
                precio = itemView.findViewById(R.id.precio);
            }
        }
}
