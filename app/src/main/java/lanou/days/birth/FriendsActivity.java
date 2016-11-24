package lanou.days.birth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.BaseActivity;
import lanou.days.birth.tool.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/23.
 */
public class FriendsActivity extends BaseSwipeActivity implements View.OnClickListener, OnRecyclerItemClickListener {

    private ImageView addIv;
    private TextView back;
    private ImageView more;
    private PopupWindow popupWindow;
    private View pop;
    private RecyclerView moreRv;
    private ArrayList<String> arrayList;
    private PopAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected int getLayout() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initViews() {
        addIv = bindView(R.id.iv_add);
        back = bindView(R.id.tv_back);
        more = bindView(R.id.iv_more);
        pop = LayoutInflater.from(this).inflate(R.layout.pop,null);
        moreRv = (RecyclerView) pop.findViewById(R.id.rv_more);
    }

    @Override
    protected void initData() {
        setClick(this,addIv,back,more);
        arrayList = new ArrayList<>();
        arrayList.add("生日倒数");
        arrayList.add("出生天数");
        arrayList.add("月份");
        arrayList.add("生肖");
        manager = new LinearLayoutManager(this);

    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_add:
                startActivity(new Intent(this,AddFriendsActivity.class));
                break;
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.iv_more:
                if (popupWindow == null||!popupWindow.isShowing()){
                    initMorePop();
                }
                break;
        }
    }
    public void initMorePop(){
        popupWindow = new PopupWindow(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(pop);
        adapter = new PopAdapter();
        Log.d("FriendsActivity", "arrayList:" + arrayList);
        adapter.setArrayList(arrayList);
        moreRv.setAdapter(adapter);
        moreRv.setLayoutManager(manager);
        popupWindow.showAsDropDown(more);
        adapter.setOnRecyclerItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }
}
