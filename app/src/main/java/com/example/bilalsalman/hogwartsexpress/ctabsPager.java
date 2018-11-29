package com.example.bilalsalman.hogwartsexpress;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;


public class ctabsPager extends FragmentStatePagerAdapter {


    String [] titles=new String[]{"Route","Trains","Checkout"};

    public ctabsPager(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                CFragment1 cFragment1=new CFragment1();
                return cFragment1;
            case 1:
                Cfragment2 cFragment2=new Cfragment2();
                return cFragment2;
            case 2:
                CFragment3 cFragment3=new CFragment3();
                return cFragment3;
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
