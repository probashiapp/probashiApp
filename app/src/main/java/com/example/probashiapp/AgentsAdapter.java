package com.example.probashiapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AgentsAdapter extends RecyclerView.Adapter<AgentsAdapter.AgentsViewHolder> {

    private ArrayList<Agents> agentsList;
    private OnitemClickListener mListener;

    public AgentsAdapter(ArrayList<Agents> agentsList) {
        this.agentsList = agentsList;
    }

    public void setOnItemClickListener(OnitemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AgentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_item, parent, false);
        return new AgentsViewHolder(v, mListener);


    }

    @Override
    public void onBindViewHolder(@NonNull AgentsViewHolder holder, int position) {
        Agents agent = agentsList.get(position);
        holder.name.setText(agent.getName());
        holder.address.setText(agent.getAddress());
        holder.city.setText(agent.getCity());

    }

    @Override
    public int getItemCount() {
        return agentsList.size();
    }

    public interface OnitemClickListener {
        void onItemClick(int position);

    }

    public static class AgentsViewHolder extends RecyclerView.ViewHolder {

        public TextView name, address, city;

        public AgentsViewHolder(View itemView, final OnitemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
            address = itemView.findViewById(R.id.address_tv);
            city = itemView.findViewById(R.id.city_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
