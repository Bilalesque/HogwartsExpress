package com.example.bilalsalman.hogwartsexpress;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class tabsPager extends FragmentStatePagerAdapter {

    String [] titles=new String[]{"Trains","Passengers","Employee","FireBase"};

    public tabsPager(FragmentManager fm)
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
                BlankFragment blankFragment=new BlankFragment();
                return blankFragment;
            case 1:
                BlankFragment2 blankFragment2=new BlankFragment2();
                return blankFragment2;
            case 2:
                BlankFragment3 blankFragment3=new BlankFragment3();
                return blankFragment3;
            case 3:
                BlankFragment4 blankFragment4=new BlankFragment4();
                return blankFragment4;

        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 4;
    }
}
