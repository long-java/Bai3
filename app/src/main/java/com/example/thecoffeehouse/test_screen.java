package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class test_screen extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tab1,tab2,tab3;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen);
        tabLayout = findViewById(R.id.tablayout);
        tab1 =findViewById(R.id.tab1);
        tab2 =findViewById(R.id.tab2);
        tab3 =findViewById(R.id.tab3);
        viewPager =findViewById(R.id.viewpaper);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
//        pagerAdapter = new com.example.thecoffeehouse.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setSelectedItemId(R.id.delivery);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.news:
//                        startActivity(new Intent(getApplicationContext(),Home.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.delivery:
////                        startActivity(new Intent(getApplicationContext(),orderScreen.class));
////                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.store:
//                        startActivity(new Intent(getApplicationContext(),store_screen.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.account:
//                        startActivity(new Intent(getApplicationContext(),account_screen.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
    }

}