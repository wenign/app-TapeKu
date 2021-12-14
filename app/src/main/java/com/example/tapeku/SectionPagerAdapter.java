package com.example.tapeku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tapeku.RealtimeFragment;
import com.example.tapeku.ProgressFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {

    public SectionPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RealtimeFragment();
                break;
            case 1:
                fragment = new ProgressFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}