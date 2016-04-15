package com.mohan.ben.kingdoms;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

//Main class, first page that will appear after signup page
//Uses recyclerview to display all Kingdom pages
//Currently uses in app generated kingdoms, Retrofit and Picasso not yet successfully integrated
public class Main extends AppCompatActivity implements recAdapter.ClickListener {

    private recAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String vEmail;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new recAdapter(getDataSet(), this.getApplicationContext());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        vEmail = pref.getString("UserEmail", "Your Email Goes Here");
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.showOverflowMenu();
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(vEmail);


    }

    //Generates Kingdom objects with numbers 1-20
    private ArrayList<Kingdom> getDataSet() {
        ArrayList results = new ArrayList<Kingdom>();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://challenge2015.myriadapps.com").build();
//        KingdomService kingserv = retrofit.create(KingdomService.class);
//        kingserv.kingdoms();
        for (int index = 0; index < 20; index++) {
            Kingdom obj = new Kingdom("Kingdom " + index);
            results.add(index, obj);
        }
        return results;
    }


//Creates Options menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu m) {
        getMenuInflater().inflate(R.menu.activity_actions, m);
        return true;
    }
// Logout button functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean m = false;
        if (item.getItemId() == R.id.log_off) {
            getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit().remove("UserEmail");
            getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit().putBoolean("firstRun", true).apply();
            Intent i = new Intent(getApplicationContext(), Signup.class);
            startActivity(i);
            m = true;
        }
        return m;
    }

    //Listener for recyclerview items, opens the KingdomActivity class
    @Override
    public void itemClicked(View view, int position) {
        Intent i = new Intent(getApplicationContext(), KingdomActivity.class);
        i.putExtra("Kingdom", mAdapter.getItem(position));
        startActivity(i);
    }
}
