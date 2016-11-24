package lanou.days.note;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.BaseFragment;

/**
 * Created by dllo on 16/11/22.
 */
public class NoteFragment extends BaseFragment  {
    private PullToRefreshListView lv;
    private ArrayList<String> mArrayList;
    @Override
    protected void initData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mArrayList.add("" + i);
        }
        final NoteAdapter adapter = new NoteAdapter();
        adapter.setArrayList(mArrayList);
        lv.setAdapter(adapter);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Toast.makeText(getContext(), "刷新了", Toast.LENGTH_SHORT).show();
                // 延时1秒,进行刷新
                lv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.onRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_note);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_note;
    }



}
