package com.tresor.home.activity;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.tresor.R;
import com.tresor.common.TresorActivity;
import com.tresor.home.fragment.SearchFragment;
import com.tresor.home.fragment.ListFinancialHistoryFragment;
import com.tresor.home.fragment.StatisticFragment;
import com.tresor.profile.ProfilePageActivity;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class HomeActivity extends TresorActivity {

    private MaterialViewPager homePager;

    private ListFinancialHistoryFragment listFinancialHistoryFragment;

    private FloatingActionButton historicalFloatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        homePager = (MaterialViewPager) findViewById(R.id.home_pager);
        homePager.getViewPager().setAdapter(homePagerAdapter(getFragmentManager()));
        historicalFloatingActionButton = (FloatingActionButton)
                findViewById(R.id.history_floating_action_button);
        historicalFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFinancialHistoryFragment.onHomeButtonFabClicked();
            }
        });
        final Toolbar toolbar = homePager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        homePager.setMaterialViewPagerListener(pagerListener());
        homePager.getViewPager().setOffscreenPageLimit(homePager.getViewPager().getAdapter().getCount());
        homePager.getPagerTitleStrip().setViewPager(homePager.getViewPager());
    }

    private FragmentStatePagerAdapter homePagerAdapter(FragmentManager fragmentManager) {
        return new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        listFinancialHistoryFragment = new ListFinancialHistoryFragment();
                        return listFinancialHistoryFragment;
                    case 1:
                        return new SearchFragment();
                    case 2:
                        return new StatisticFragment();
                    default:
                        listFinancialHistoryFragment = new ListFinancialHistoryFragment();
                        return listFinancialHistoryFragment;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Spending";
                    case 1:
                        return "History";
                    case 2:
                        return "Statistic";
                }
                return "";
            }
        };
    }

    private MaterialViewPager.Listener pagerListener() {
        return new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                }
                return null;
            }
        };
    }

}
