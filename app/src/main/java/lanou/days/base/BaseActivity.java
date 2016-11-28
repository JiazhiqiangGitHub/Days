package lanou.days.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/10/21.
 * activity的基类
 */
public abstract class BaseActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.绑定布局
        setContentView(getLayout());
        //2.初始化组件
        initViews();
        //3.初始化数据
        initData();

    }
    protected abstract int getLayout();
     //初始化组件,各种findViewById
    protected abstract void initViews();
    //初始化数据,基本上就是 拉取网络数据
    protected abstract void initData();

    //简化findViewById 省去强转的过程
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
    protected <T extends View> T bindView(View view,int id){
        return (T) view.findViewById(id);
    }
    //简化很多的监听事件
    protected void setClick(View.OnClickListener clickListener,View ... views){
        for(View view : views){
            view.setOnClickListener(clickListener);
        }
    }
}
