package lanou.days.news.read;

import android.os.CountDownTimer;
import android.util.Log;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.base.OkHttpManager;
import lanou.days.base.ResponseCallBack;
import lanou.days.bean.ReadBean;
import lanou.days.values.Values;

/**
 * Created by dllo on 16/11/24.
 */
public class ReadFragment extends BaseFragment {
    private PullLoadMoreRecyclerView recyclerView;
    private ReadAdapter readAdapter;
    private int j;

    @Override
    protected void initData() {
        OkHttpGet();
        SetOnPullLoadMore();

    }

    private void SetOnPullLoadMore() {
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                recyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        OkHttpGet2();
                        recyclerView.setPullLoadMoreCompleted();
                    }
                };
                countDownTimer.start();

            }
        });
    }

    private void OkHttpGet2() {
        j = j + 20;
        String getUrl = Values.URL_READTWO + j;
        OkHttpManager.getInstance().get(getUrl, ReadBean.class, new ResponseCallBack<ReadBean>() {
            @Override
            public void onResponse(ReadBean readBean) {
                readAdapter = new ReadAdapter(getContext());
                readAdapter.setBean(readBean);
                recyclerView.setAdapter(readAdapter);
                recyclerView.setGridLayout(1);

            }

            @Override
            public void onError(Exception exception) {
                Log.d("ReadFragment", "dsahjbdiy");
                Log.d("ReadFragment", exception.getMessage());

            }
        });
    }

    private void OkHttpGet() {
        OkHttpManager.getInstance().get(Values.URL_READONE, ReadBean.class, new ResponseCallBack<ReadBean>() {
            @Override
            public void onResponse(ReadBean readBean) {
                readAdapter = new ReadAdapter(getContext());
                readAdapter.setBean(readBean);
                recyclerView.setAdapter(readAdapter);
                recyclerView.setGridLayout(1);

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
