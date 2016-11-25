package lanou.days.news.read;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.base.OkHttpManager;
import lanou.days.base.ResponseCallBack;
import lanou.days.bean.ReadBean;

/**
 * Created by dllo on 16/11/24.
 */
public class ReadFragment extends BaseFragment{
    private RecyclerView recyclerView;
    private ReadAdapter readAdapter;
    @Override
    protected void initData() {

            String getUrl = "http://api.maoyan.com/mallpro/recommended.json?";
            OkHttpManager.getInstance().get(getUrl,ReadBean.class,new ResponseCallBack<ReadBean>() {
                @Override
                public void onResponse(ReadBean readBean) {
                    readAdapter = new ReadAdapter();
                    readAdapter.setBean(readBean);
                    recyclerView.setAdapter(readAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }

                @Override
                public void onError(Exception exception) {
                    Log.d("ReadFragment", "dsahjbdiy");
                    Log.d("ReadFragment", exception.getMessage());
                }
            });


    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.lv_news_read);




    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_news_read;
    }


}
