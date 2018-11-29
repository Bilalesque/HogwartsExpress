package com.example.bilalsalman.hogwartsexpress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> list;

    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView resloc;

        public SearchViewHolder(View itemView) {
            super(itemView);
            resloc=itemView.findViewById(R.id.resloc);
        }
    }

    public LocationAdapter(Context c, ArrayList<String> l)
    {
        this.context=c;
        this.list=l;
    }

    @Override
    public LocationAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loclist,parent,false);

        return new LocationAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.resloc.setText(list.get(position));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
