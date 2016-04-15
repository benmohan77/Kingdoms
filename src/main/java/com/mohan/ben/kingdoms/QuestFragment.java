package com.mohan.ben.kingdoms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//Fragment object that shows quest info and allows scrolling between them.
public class QuestFragment extends Fragment {
    private static Quest questObj;

    // newInstance constructor for creating fragment with arguments
    public static QuestFragment newInstance(int page) {
        QuestFragment questFragment = new QuestFragment();
        Log.d("Page", Integer.toString(page));
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        questFragment.setArguments(args);
        questObj = QuestActivity.quests.get(page);
        return questFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    //Sets text values of all items dynamically, so quest object can be inserted at will
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quest, container, false);

        ImageView questImg = (ImageView) view.findViewById(R.id.questImage);
        questImg.setImageResource(questObj.getImg());

        TextView questInfo = (TextView) view.findViewById(R.id.questDesc);
        questInfo.setText(questObj.getInfo());

        ImageView giverImg = (ImageView) view.findViewById(R.id.questGiverImg);
        giverImg.setImageResource(questObj.getGiverImg());

        TextView giverName = (TextView) view.findViewById(R.id.questGiverName);
        giverName.setText(questObj.getGiverName());

        TextView giverInfo = (TextView) view.findViewById(R.id.questGiverDesc);
        giverInfo.setText(questObj.getGiverInfo());






        return view;
    }
}

