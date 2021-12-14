package com.example.tapeku;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.tapeku.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tabRealtime,
            R.string.tabProgress
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionPagerAdapter sectionsPagerAdapter = new SectionPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}
