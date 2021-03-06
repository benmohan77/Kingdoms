package com.mohan.ben.kingdoms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

//Recycler view adapter used for kingdom list in Main.java
public class recAdapter extends RecyclerView.Adapter<recAdapter.KingdomViewHolder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Kingdom> mDataset;
    private ArrayList<Quest> mDataset2;
    private Context context;
    private ClickListener clickListener;


//View Holder for kingdom object
    public class KingdomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        ImageView img;



        public KingdomViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView2);
            img = (ImageView) itemView.findViewById(R.id.imgView);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }

    }


    public recAdapter(ArrayList<Kingdom> Kingdoms, Context c) {
        mDataset = Kingdoms;
        this.context = c;
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
        holder.img.setImageResource(mDataset.get(position).getIcon());
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
    public Kingdom getItem(int index){
        return mDataset.get(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener click) {
        this.clickListener = click;
    }


}
