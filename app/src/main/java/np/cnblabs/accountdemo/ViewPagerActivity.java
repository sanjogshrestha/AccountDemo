package np.cnblabs.accountdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.cnblabs.accountdemo.fragment.DashboardFragment;
import np.cnblabs.accountdemo.fragment.HomeFragment;
import np.cnblabs.accountdemo.fragment.NotificationFragment;

/**
 * Created by sanjogstha on 12/12/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    CustomViewPagerAdapter customAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customAdapter = new CustomViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(customAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter{
        Context context;
        String[] titleList = {"Home", "Dashboard", "Notification"};

        CustomViewPagerAdapter(FragmentManager supportFragmentManager, Context context) {
            super(supportFragmentManager);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment selectedFragment = null;
            switch (position){
                case 0:
                    selectedFragment = HomeFragment.newInstance();
                    break;

                case 1:
                    selectedFragment = DashboardFragment.newInstance();
                    break;

                case 2:
                    selectedFragment = NotificationFragment.newInstance();
                    break;
            }
            return selectedFragment;
        }

        @Override
        public int getCount() {
            return titleList.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList[position];
        }
    }
}
