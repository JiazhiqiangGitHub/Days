package lanou.days.news.technology;

import android.util.Log;
import android.widget.ListView;

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


    @Override
    protected void initData() {
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

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_technology);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_technology;
    }
}
