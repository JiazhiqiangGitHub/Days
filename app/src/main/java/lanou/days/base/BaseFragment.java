package lanou.days.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.days.R;



/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayout() == 0){
            //如果Fragment没有指定布局
            //默认就给一个空布局 预防报错崩溃
            return inflater.inflate(R.layout.null_layout,container,false);
        }
        return inflater.inflate(getLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    protected <T extends View> T bindView(int id){
        return (T) getView().findViewById(id);
    }
        //指定在那个view里findViewById
    protected <T extends View> T bindView(View view,int id){
        return (T) view.findViewById(id);
    }
    //简化很多的监听事件
    protected void setClick(View.OnClickListener clickListener,View ... views){
        for(View view : views){
            view.setOnClickListener(clickListener);
        }
    }
    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
    //View的点击事件
    protected void setItemOnClick(View.OnClickListener clickListener, View ...views){
        int i = 0;
        for (View view : views){
            if (view == null) {
                Log.d("BaseFragment-click", "i:" + i);
            }else {

                view.setOnClickListener(clickListener);
            }
            i++;
        }
    }



}
