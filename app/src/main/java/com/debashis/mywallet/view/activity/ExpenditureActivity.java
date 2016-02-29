package com.debashis.mywallet.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.R;
import com.debashis.mywallet.adapter.ExpenditurePagerAdapter;
import com.debashis.mywallet.view.fragment.CommonFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Debashis on 25/2/16.
 */
@EActivity(R.layout.expenditure_view_page_layout)
public class ExpenditureActivity extends AppCompatActivity {

    @ViewById(R.id.view_pager)
    ViewPager viewPager;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.tabs)
    TabLayout tabLayout;

    private ActionBar mActionBar;

    @AfterViews
    public void init(){
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setUpViewpager(ViewPager viewpager){
        ExpenditurePagerAdapter adapter = new ExpenditurePagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new CommonFragment(), Constant.BANK_ACCOUNT_TAB);
        adapter.addFragments(new CommonFragment(), Constant.CREDIT_CARD_TAB);
        adapter.addFragments(new CommonFragment(), Constant.CASH_TAB);

        viewpager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.setting_menu){
            Intent intent = new Intent(this, SettingActivity_.class);
            startActivityForResult(intent, Constant.REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
