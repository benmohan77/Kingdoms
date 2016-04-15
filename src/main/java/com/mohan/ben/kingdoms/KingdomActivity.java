package com.mohan.ben.kingdoms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

//Activity that dynamically shows Kingdom info based on input kingdom object, also holds recyclerview for quest information
public class KingdomActivity extends AppCompatActivity implements questRecAdapter.ClickListener{
    private questRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Kingdom king = (Kingdom) extras.get("Kingdom");
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_kingdom);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mAdapter = new questRecAdapter(king.getQuests(), this.getApplicationContext());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        ImageView img = (ImageView) findViewById(R.id.kingdomImage);
        img.setImageResource(king.getImg());

        TextView clim = (TextView) findViewById(R.id.kingdomClimateData);
        clim.setText(king.getClimate());

        TextView pop = (TextView) findViewById(R.id.kingdomPopData);
        pop.setText(king.getPop());

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mActionBarToolbar.showOverflowMenu();
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(king.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);








    }
    //Home button functionality
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Recyclerview click listener
    @Override
    public void itemClicked(View view, int position) {
        Intent i = new Intent(getApplicationContext(), QuestActivity.class);
        i.putExtra("Quest", mAdapter.getItem(position));
        i.putExtra("Quests", mAdapter.getmDataset());
        i.putExtra("Position", position);
        startActivity(i);
    }
}
