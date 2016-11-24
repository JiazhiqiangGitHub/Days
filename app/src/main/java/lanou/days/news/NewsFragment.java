package lanou.days.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.BaseFragment;

/**
 * Created by dllo on 16/11/23.
 */
public class NewsFragment extends BaseFragment {
    private TabLayout tb;
    private TabAdapter adapter;
    private ViewPager vp;
    private ArrayList<Fragment> fragment;
    @Override
    protected void initDate() {

        adapter = new TabAdapter(getChildFragmentManager());
        fragment = new ArrayList<>();
        fragment.add(new ReadFragment());
        fragment.add(new TelchnologyFragment());
        adapter.setFragments(fragment);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_news);
        tb = bindView(R.id.tb_news);
    }

    @Override
    protected int getLayout() {
        return R.layout.news_fragment;
    }
}
