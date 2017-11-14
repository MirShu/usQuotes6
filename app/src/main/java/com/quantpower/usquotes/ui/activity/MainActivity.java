package com.quantpower.usquotes.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.quantpower.usquotes.ui.fragment.NavigationFragment;
import com.quantpower.usquotes.utils.BackHandlerHelper;
import com.quantpower.usquotes.utils.UIHelper;
import com.quantpower.usquotes.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private NavigationFragment mNavigationFragment;
    private long mExitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCurrentFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mNavigationFragment == null) {
            mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
        }
        transaction.replace(R.id.frame_content, mNavigationFragment);

    }

    private void setCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
        transaction.replace(R.id.frame_content, mNavigationFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
        }
        transaction.commit();
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    public interface FragmentBackHandler {
        boolean onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if ((System.currentTimeMillis() - mExitTime) < 2000) {
                super.onBackPressed();
            } else {
                UIHelper.toastMessage(MainActivity.this, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            }
        }
    }
}
