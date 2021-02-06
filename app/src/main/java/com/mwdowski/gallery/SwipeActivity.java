package com.mwdowski.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class SwipeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SwipeViewAdapter pagerAdapter;
    private ArrayList<Photo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        data = Variables.photos;
        setUpViewPager();
        viewPager.setCurrentItem(getIntent().getIntExtra("image_index", 0));
    }

    private void setUpViewPager() {
        viewPager = (ViewPager2) findViewById(R.id.pager);
        pagerAdapter = new SwipeViewAdapter(data, this);
        viewPager.setAdapter(pagerAdapter);
    }
}