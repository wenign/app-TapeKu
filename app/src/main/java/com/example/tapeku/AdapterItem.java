package com.example.tapeku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ItemViewHolder> {

    Context context;
    ArrayList<DatabaseHistory> databaseHistoryArrayList;

    public AdapterItem(Context context, ArrayList<DatabaseHistory> databaseHistoryArrayList) {
        this.context = context;
        this.databaseHistoryArrayList = databaseHistoryArrayList;
    }

    @NonNull
    @Override
    public AdapterItem.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItem.ItemViewHolder holder, int position) {
        holder.viewBind(databaseHistoryArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return databaseHistoryArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView dataAlkohol, dataWaktu;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            dataAlkohol = itemView.findViewById(R.id.textDataAlkohol);
            dataWaktu = itemView.findViewById(R.id.textDataWaktu);
        }

        public void viewBind(DatabaseHistory DatabaseHistory) {
            dataAlkohol.setText(DatabaseHistory.getValue() +" %");
            dataWaktu.setText(DatabaseHistory.getTimeStamp());
        }
    }
}
