package lanou.days.note;

import android.util.Log;
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
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.write.WriteBean;

/**
 * 所有笔记的Fragment
 * Created by 张家鑫 on 16/11/22.
 */
public class NoteFragment extends BaseFragment  {
    private PullToRefreshListView lv;
    private NoteAdapter mAdapter;

    @Override
    protected void initData() {
        mAdapter = new NoteAdapter();
        getBeanData(); // 去拿对应账号的内容
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

    private void getBeanData() {
        BmobUser user = BmobUser.getCurrentUser();
        BmobQuery<WriteBean> query = new BmobQuery<WriteBean>();
        query.addWhereEqualTo("author",user); // 查询当前用户的所有帖子
        query.order("-updatedAt");
        query.include("author");// 把发布人的信息查出来
        query.findObjects(new FindListener<WriteBean>() {
            @Override
            public void done(List<WriteBean> object, BmobException e) {
                if (e == null){
                    //成功
                        mAdapter.setList(object);
                        lv.setAdapter(mAdapter);
                } else {
                    // 失败
                    Log.d("NoteFragment", e.getMessage());
                    Toast.makeText(getContext(), "失...失败了喵", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
