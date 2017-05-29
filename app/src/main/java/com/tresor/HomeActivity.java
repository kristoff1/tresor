package com.tresor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tresor.home.fragment.ListFinancialHistoryFragment;

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
        homeTab = (TabLayout) findViewById(R.id.home_tab);
        homePager = (ViewPager) findViewById(R.id.home_pager);
        homeTab.addTab(homeTab.newTab().setText("History"));
        homeTab.addTab(homeTab.newTab().setText("Statistic"));
        homeTab.addTab(homeTab.newTab().setText("Options"));
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
                        return new ListFinancialHistoryFragment();
                    case 1:
                        return new ListFinancialHistoryFragment();
                    case 2:
                        return new ListFinancialHistoryFragment();
                    default:
                        return new ListFinancialHistoryFragment();
                }
            }

            @Override
            public int getCount() {
                return homeTab.getTabCount();
            }
        };
    }

}
