package lanou.days.news.technology;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.dalong.francyconverflow.FancyCoverFlow;

import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.base.OkHttpManager;
import lanou.days.base.ResponseCallBack;
import lanou.days.bean.TechnologyBean;
import lanou.days.values.Values;

/**
 * Created by dllo on 16/11/24.
 */
public class TelchnologyFragment extends BaseFragment {
    private ListView lv;
    private TechnologyAdapter adapter;
    private FancyCoverFlow mfancyCoverFlow;
    private MyFancyCoverFlowAdapter flowAdapter;
    private View headview;


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
        int[] images = {R.mipmap.ic_launcher,R.mipmap.test11,R.mipmap.test11};

        mfancyCoverFlow = (FancyCoverFlow) headview.findViewById(R.id.fancy_cover_flow);
        flowAdapter = new MyFancyCoverFlowAdapter(getActivity(), images);
        mfancyCoverFlow.setAdapter(flowAdapter);
        flowAdapter.notifyDataSetChanged();
        mfancyCoverFlow.setUnselectedAlpha(0.5f);//透明度
        mfancyCoverFlow.setUnselectedSaturation(0.5f);//设置选中的饱和度
        mfancyCoverFlow.setUnselectedScale(0.3f);//设置选中的规模
        mfancyCoverFlow.setSpacing(0);//设置间距
        mfancyCoverFlow.setMaxRotation(0);//设置最大旋转
        mfancyCoverFlow.setScaleDownGravity(0.5f);
        mfancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        mfancyCoverFlow.setSelection(Integer.MAX_VALUE / 2);
    }
    @Override
    protected void initView() {
        headview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_technology_head,null);
        lv = bindView(R.id.lv_technology);
        lv.addHeaderView(headview);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_technology;
    }
}
