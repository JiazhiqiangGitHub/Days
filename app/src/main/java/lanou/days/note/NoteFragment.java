package lanou.days.note;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import lanou.days.MainActivity;
import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.write.WriteBean;

/**
 * 所有笔记的Fragment
 * Created by 张家鑫 on 16/11/22.
 */
public class NoteFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private ListView mMainListView;
    private NoteDirectoryAdapter mDirectoryAdapter;
    private NoteAdapter mAdapter;
    private String mType;
    private static int Tag = 0;

    @Override
    protected void initData() {
        mAdapter = new NoteAdapter();
        mDirectoryAdapter = new NoteDirectoryAdapter();
        mMainListView.setAdapter(mDirectoryAdapter);
//        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//                Toast.makeText(getContext(), "刷新了", Toast.LENGTH_SHORT).show();
//                // 延时1秒,进行刷新
//                lv.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        lv.onRefreshComplete();
//                    }
//                }, 1000);
//            }
//        });

    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_note);
        lv.setVisibility(View.INVISIBLE);
        mMainListView = bindView(R.id.lv_directory);
        mMainListView.setVisibility(View.VISIBLE);
        mMainListView.setOnItemClickListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    protected int getLayout() {
            return R.layout.fragment_note;
    }

    private void getBeanData() {
        BmobUser user = BmobUser.getCurrentUser();
        if (user != null) {
            BmobQuery<WriteBean> eq2 = new BmobQuery<WriteBean>();
            eq2.addWhereEqualTo("author",user); // 查询当前用户的所有帖子
//            eq2.order("-updatedAt");
//            eq2.include("author");// 把发布人的信息查出来
            BmobQuery<WriteBean> eq1 = new BmobQuery<WriteBean>();
            eq1.addWhereEqualTo("type",mType); // 查询当前的TYPE
            List<BmobQuery<WriteBean>> andQuerys = new ArrayList<BmobQuery<WriteBean>>();
            andQuerys.add(eq1);
            andQuerys.add(eq2);

            BmobQuery<WriteBean> query = new BmobQuery<WriteBean>();
            query.and(andQuerys);
            query.order("-updatedAt");
//            query.include("author");// 把发布人的信息查出来
            query.findObjects(new FindListener<WriteBean>() {
                @Override
                public void done(List<WriteBean> object, BmobException e) {
                    if (e == null){
                        //成功
                        Log.d("NoteFragment", object.get(0).getTitle());

                        if (object.get(0).getType().equals("")) {
                            Toast.makeText(getContext(), "什么都没有", Toast.LENGTH_SHORT).show();
                        } else
                            mAdapter.setList(object);
                            lv.setAdapter(mAdapter);
                        Tag = 1;

                    } else {
                        // 失败
                        Log.d("NoteFragment", e.getMessage());
                        Toast.makeText(getContext(), "失...失败了喵,请检查文件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "请先登录喵", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.lv_note:
                Log.d("NoteFragment", "点击");
                Intent intent = new Intent();
                intent.setClass(getActivity(),NoteDetail.class);
                String[] all = mAdapter.getText(i);
                intent.putExtra("title",all[0]);
                intent.putExtra("time",all[1]);
                intent.putExtra("content",all[2]);
                intent.putExtra("objectID",all[3]);
                startActivity(intent);
                break;
            case R.id.lv_directory:
                Log.d("NoteFragment", "what");
                TextView typeName = bindView(view,R.id.tv_note_main_type);
                mType = typeName.getText().toString();
                getBeanData(); // 去拿对应账号的内容
                lv.setVisibility(View.VISIBLE);
                mMainListView.setVisibility(View.INVISIBLE);

                break;
        } 

    }

//    @Override
//    public void onResume() {
//        if (Tag == 1) {
//            getBeanData();
//        }
//        super.onResume();
//    }
}
