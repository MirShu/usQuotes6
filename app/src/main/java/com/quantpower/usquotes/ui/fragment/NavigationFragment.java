package com.quantpower.usquotes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.quantpower.usquotes.R;
import com.quantpower.usquotes.utils.Constants;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */
public class NavigationFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener {


    private BottomNavigationBar mBottomNavigationBar;
    private HomeFragment mHomeFragment;
    private AlertsFragment mLocationFragment;
    private FoundFragment mLikeFragment;
    private MeFragment mPersonFragment;
    private BadgeItem badge;

    public static NavigationFragment newInstance(String s) {
        NavigationFragment navigationFragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        navigationFragment.setArguments(bundle);
        return navigationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false);
        mBottomNavigationBar = (BottomNavigationBar) view.findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home2, getString(R.string.item_home)).setInactiveIconResource(R.mipmap.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.mipmap.icon_live2, getString(R.string.item_like)).setInactiveIconResource(R.mipmap.icon_live).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.mipmap.icon_sech2, getString(R.string.item_location)).setInactiveIconResource(R.mipmap.icon_sech).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.mipmap.icon_me2, getString(R.string.item_person)).setInactiveIconResource(R.mipmap.icon_me).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
        return view;
    }

    /**
     * set the default fagment
     * <p>
     * the content id should not be same with the parent content id
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        AlertsFragment homeFragment = mLocationFragment.newInstance(getString(R.string.item_home));
        transaction.replace(R.id.sub_content, homeFragment).commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                if (mLocationFragment == null) {
                    mLocationFragment = AlertsFragment.newInstance(getString(R.string.item_home));
                }
                beginTransaction.replace(R.id.sub_content, mLocationFragment);


                break;
            case 2:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(getString(R.string.item_location));
                }
                beginTransaction.replace(R.id.sub_content, mHomeFragment);
                break;
            case 3:
                if (mPersonFragment == null) {
                    mPersonFragment = MeFragment.newInstance(getString(R.string.item_person));
                }
                beginTransaction.replace(R.id.sub_content, mPersonFragment);
                break;
            case 1:
                if (mLikeFragment == null) {
                    mLikeFragment = FoundFragment.newInstance(getString(R.string.item_like));
                }
                beginTransaction.replace(R.id.sub_content, mLikeFragment);
                break;
        }
        beginTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
