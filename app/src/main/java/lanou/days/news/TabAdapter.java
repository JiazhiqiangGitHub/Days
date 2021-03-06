package lanou.days.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/24.
 */
public class TabAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragments;
    private String[] string = {"生活电台","饰品物语"};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return string[position];
    }
}
