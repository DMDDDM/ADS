package com.dmdddm.ads;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentCenter fragmentCenter;
    private FragmentTopic fragmentTopic;
    private  FragmentHome fragmentHome;
    private int lastShowFragment = 0;   //表示最后一个显示的Fragment
    private Fragment[] fragments;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                        return true;
                case R.id.navigation_notifications:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                        return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }
    private void initFragments() {
        fragmentHome = new FragmentHome();
        fragmentTopic = new FragmentTopic();
        fragmentCenter = new FragmentCenter();
        fragments = new Fragment[]{fragmentHome, fragmentTopic, fragmentCenter};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragHome, fragmentHome)
                .show(fragmentHome)
                .commit();
    }
    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragHome, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,Details.class);
        switch (view.getId()){
            case R.id.commodity:
                startActivity(intent);
                break;
            default:break;
        }
    }
//    this is test
}
