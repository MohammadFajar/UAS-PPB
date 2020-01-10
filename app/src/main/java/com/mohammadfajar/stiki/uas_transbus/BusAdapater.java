package com.mohammadfajar.stiki.uas_transbus;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BusAdapater extends RecyclerView.Adapter<BusAdapater.BusViewHolder> {
    private ArrayList<Bus> dataList;
    public BusAdapater(ArrayList<Bus> dataList){
        this.dataList = dataList;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_bus, parent,false);
        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, int position) {
       holder.tvNamaBus.setText(dataList.get(position).getNamBus());
       holder.tvTujuan.setText(dataList.get(position).getTujuan());
       holder.tvHarga.setText(dataList.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size():0;
    }

    public class BusViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNamaBus, tvTujuan, tvHarga;
        public BusViewHolder(View itemView) {
            super(itemView);
            tvNamaBus = (TextView) itemView.findViewById(R.id.tvNamaBus);
            tvTujuan= (TextView) itemView.findViewById(R.id.tvTujuan);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);

        }
    }
}
