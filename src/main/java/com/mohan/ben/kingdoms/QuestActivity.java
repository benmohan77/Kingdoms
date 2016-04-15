package com.mohan.ben.kingdoms;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import java.util.ArrayList;

//Activity that holds view pager and allows scrolling left and right between Quest Fragments
//Currently the quest fragments don't display correct information based on their arraylist
public class QuestActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    public static ArrayList<Quest> quests;
    public static class QuestPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        ArrayList<Quest> quests;

        public QuestPagerAdapter(FragmentManager fragmentManager, ArrayList<Quest> quests) {
            super(fragmentManager);
            this.quests = quests;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page, Currently Displays wrong page information often, Unknown Cause
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return QuestFragment.newInstance(0);
                case 1:
                    return QuestFragment.newInstance(1);
                case 2:
                    return QuestFragment.newInstance(2);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_quest_frag);
        Bundle extras = getIntent().getExtras();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        quests = (ArrayList<Quest>) extras.get("Quests");
        adapterViewPager = new QuestPagerAdapter(getSupportFragmentManager(), quests);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem((Integer) extras.get("Position"));
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


}
