package com.mohan.ben.kingdoms;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ben on 4/3/2016.
 */
public class recAdapter extends RecyclerView.Adapter<recAdapter.KingdomViewHolder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Kingdom> mDataset;
    private static clickListener clickListener;

    public static class KingdomViewHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;

        public KingdomViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(clickListener click) {
        this.clickListener = click;
    }

    public recAdapter(ArrayList<Kingdom> Kingdoms) {
        mDataset = Kingdoms;
    }

    @Override
    public KingdomViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_item, parent, false);

        KingdomViewHolder Kingdoms = new KingdomViewHolder(view);
        return Kingdoms;
    }

    @Override
    public void onBindViewHolder(KingdomViewHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getName());
    }

    public void addItem(Kingdom kingdom, int index) {
        mDataset.add(kingdom);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface clickListener {
        public void onItemClick(int position, View v);
    }


}
