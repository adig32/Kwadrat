package com.example.kwadrat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.EquipmentModel;
import com.example.kwadrat.R;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {
    private Context mCtx;
    private List<EquipmentModel> equipmentList;

    public EquipmentAdapter(Context mCtx, List<EquipmentModel> equipmentList) {
        this.mCtx = mCtx;
        this.equipmentList = equipmentList;
    }

    @Override
    public EquipmentAdapter.EquipmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.one_option, null);
        return new EquipmentAdapter.EquipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipmentAdapter.EquipmentViewHolder holder, int position) {
        EquipmentModel equipmentModel = equipmentList.get(position);

        holder.textViewEquipmentName.setText(equipmentModel.getEquipment_name());

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    class EquipmentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewEquipmentName;

        public EquipmentViewHolder(View itemView) {
            super(itemView);

            textViewEquipmentName = itemView.findViewById(R.id.equipment_name);
        }
    }
}
