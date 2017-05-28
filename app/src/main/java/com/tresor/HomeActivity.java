package com.tresor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class HomeActivity extends AppCompatActivity {

    private ViewPager homePager;

    private TabLayout homeTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        homeTab.addTab(homeTab.newTab().setText("Notes"));
        homeTab.addTab(homeTab.newTab().setText("Memos"));
        homeTab.addTab(homeTab.newTab().setText("Events"));
        homeTab.setTabGravity(TabLayout.GRAVITY_FILL);
        homePager.setAdapter(homePagerAdapter(getFragmentManager()));
        homePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeTab));
        homeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homePager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private FragmentStatePagerAdapter homePagerAdapter(FragmentManager fragmentManager) {
        return new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return NotesListFragment.createNoteListFragment();
                    case 1:
                        return MemoListFragment.createMemoListFragment();
                    case 2:
                        return EventListFragment.createEventList();
                    default:
                        return NotesListFragment.createNoteListFragment();
                }
            }

            @Override
            public int getCount() {
                return homeTab.getTabCount();
            }
        };
    }

}
