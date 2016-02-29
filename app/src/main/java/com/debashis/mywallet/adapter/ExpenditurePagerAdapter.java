package com.debashis.mywallet.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.view.fragment.CommonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debashis on 26/2/16.
 */
public class ExpenditurePagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> pageTitleList = new ArrayList<>();

    public ExpenditurePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int expenditureType = 0;
        switch(position){
            case 0:
                expenditureType = 1;
                break;
            case 1:
                expenditureType = 2;
                break;
            case 2:
                expenditureType = 3;
                break;
        }
        Fragment fragment = fragmentList.get(position);
        Bundle args = new Bundle();
        args.putInt(Constant.WalletKeys.Key_Expenditure_Type, expenditureType);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return Constant.TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitleList.get(position);
    }

    public void addFragments(Fragment fragment, String fragmentTitle){
        fragmentList.add(fragment);
        pageTitleList.add(fragmentTitle);
    }
}
