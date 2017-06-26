package com.tresor.home.activity;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.tresor.R;
import com.tresor.common.TresorActivity;
import com.tresor.home.fragment.ListFinancialHistoryFragment;
import com.tresor.home.fragment.StatisticFragment;
import com.tresor.profile.ProfilePageActivity;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class HomeActivity extends TresorActivity {

    private ViewPager homePager;

    private TabLayout homeTab;

    private LinearLayout bannerBudget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        homeTab = (TabLayout) findViewById(R.id.home_tab);
        homePager = (ViewPager) findViewById(R.id.home_pager);
        bannerBudget = (LinearLayout)findViewById(R.id.header_budget_layout);
        bannerBudget.setOnClickListener(onBannerClickedListener());
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
                        return new StatisticFragment();
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

    private View.OnClickListener onBannerClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfilePageActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options;
                    options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
                            bannerBudget, "robot");
                    startActivity(intent, options.toBundle());
                } else startActivity(intent);
                //TODO make transition animation
            }
        };
    }

}
