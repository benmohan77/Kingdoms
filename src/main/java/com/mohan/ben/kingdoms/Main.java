package com.mohan.ben.kingdoms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String vEmail;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new recAdapter(getDataSet());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        vEmail = bundle.getString("email");
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.showOverflowMenu();
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(vEmail);
    }

    private ArrayList<Kingdom> getDataSet() {
        ArrayList results = new ArrayList<Kingdom>();
        for (int index = 0; index < 20; index++) {
            Kingdom obj = new Kingdom("Kingdom " + index);
            results.add(index, obj);
        }
        return results;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu m) {
        getMenuInflater().inflate(R.menu.activity_actions, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean m = false;
        if (item.getItemId() == R.id.log_off) {
            editor.remove(vEmail).commit();
            Intent i = new Intent(getApplicationContext(), Signup.class);
            startActivity(i);
            m = true;
        }
        return m;
    }
}
