package lanou.days.news.technology;

import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.okhttp.OkHttpManager;
import lanou.days.okhttp.ResponseCallBack;
import lanou.days.bean.TechnologyBean;
import lanou.days.values.Values;

/**
 * Created by dllo on 16/11/24.
 */
public class TelchnologyFragment extends BaseFragment {
    private ListView lv;
    private TechnologyAdapter adapter;
    private ViewPager vp;
    private RelativeLayout mViewPagerContainer;
    private View headview;
    private ViewPagerAdapter vpAdapter;


    @Override
    protected void initData() {

        headViewinit();

        OkHttpManager.getInstance().get(Values.URL_TECHNOLOGY, TechnologyBean.class, new ResponseCallBack<TechnologyBean>() {
            @Override
            public void onResponse(TechnologyBean technologyBean) {
                adapter = new TechnologyAdapter(getContext());
                adapter.setBean(technologyBean);
                Log.d("bbbTelchnologyFragment", "technologyBean:" + technologyBean.getData().getItems().get(0).getTitle());
                lv.setAdapter(adapter);
            }
            @Override
            public void onError(Exception exception) {
                Log.d("eReadFragment", "dsahjbdiy");
                Log.d("eReadFragment", exception.getMessage());
            }
        });
    }

    private void headViewinit() {
        //clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。
        // 默认情况下，clipChild被设置为true。 也就是不允许进行扩展绘制。
        vp.setClipChildren(false);
        mViewPagerContainer.setClipChildren(false);
        vpAdapter = new ViewPagerAdapter(getContext());
        vp.setAdapter(vpAdapter);
        //设置ViewPager切换效果
        vp.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置预加载数量
        vp.setOffscreenPageLimit(3);
        //设置每页之间的左右间隔
        vp.setPageMargin(30);
        //将容器的触摸事件反馈给ViewPager
        mViewPagerContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return vp.dispatchTouchEvent(event);
            }
        });
    }
    @Override
    protected void initView() {
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_technology_head,null);
        vp = bindView(headview,R.id.vp_technology);
        mViewPagerContainer = bindView(headview,R.id.vp_container);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vp.getLayoutParams();
        lp.leftMargin = screenWidth / 3;
        lp.rightMargin = screenWidth / 3;
        vp.setLayoutParams(lp);

        lv = bindView(R.id.lv_technology);
        lv.addHeaderView(headview);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_technology;
    }



}
