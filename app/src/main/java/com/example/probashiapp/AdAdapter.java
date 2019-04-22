package com.example.probashiapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {

    private ArrayList<Ad> adtList;
    private OnitemClickListener mListener;

    public interface OnitemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnitemClickListener listener){
        mListener = listener;
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder{

        public TextView title,country,basicpay,visagrade;

        public AdViewHolder(View itemView, final OnitemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            country = itemView.findViewById(R.id.country_tv);
            basicpay = itemView.findViewById(R.id.basicpay_tv);
            visagrade = itemView.findViewById(R.id.visagrade_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public AdAdapter(ArrayList<Ad> adList){
        this.adtList = adList;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item,parent,false);
        return new AdViewHolder(v,mListener);


    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        Ad ad = adtList.get(position);
        holder.title.setText(ad.getTitle());
        holder.country.setText(ad.getCountry());
        holder.basicpay.setText(ad.getBasic_pay());
        holder.visagrade.setText(ad.getVisa_grade());

    }

    @Override
    public int getItemCount() {
        return adtList.size();
    }

}
