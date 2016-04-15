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

/**
 * RecyclerView Adapter for quest objects
 */
public class questRecAdapter extends RecyclerView.Adapter<questRecAdapter.QuestViewHolder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Quest> mDataset;
    private Context context;
    private ClickListener clickListener;


    //ViewHolder for quest objects
    public class QuestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        ImageView img;



        public QuestViewHolder(View itemView) {
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


    public questRecAdapter(ArrayList<Quest> Quests, Context c) {
        mDataset = Quests;
        this.context = c;
    }

    @Override
    public QuestViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_item, parent, false);

        QuestViewHolder quest = new QuestViewHolder(view);
        return quest;
    }

    @Override
    public void onBindViewHolder(QuestViewHolder holder, int position) {
        holder.img.setImageResource(mDataset.get(position).getGiverImg());
        holder.label.setText(mDataset.get(position).getName());

    }
    //Getters and setters
    public ArrayList<Quest> getmDataset(){
        return mDataset;
    }
    public void addItem(Quest quest, int index) {
        mDataset.add(quest);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    public Quest getItem(int index){
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
