package lanou.days.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/31.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{
    //SparesArray用法和HashMap一样
    //但是Key 固定是int类型
    //用它来存放所有的View,key就是View的id
    private SparseArray<View> views;
    private Context context;

    private View itemView; //行布局
    public CommonViewHolder(View itemView) {
        super(itemView);
       this.itemView = itemView;
        views = new SparseArray<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //使用泛型来取消强转
    //通过View的id来获得指定的View 如果该View没有赋值,就先执行findViewByid
    //然后把他放到集合里
    public <T extends View>T getView(int id){
        View view = views.get(id);
        if (view == null){
            //证明SparseArray里没有这个View
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }


    //专门给ListView使用的方法
    //adapter中的方法
    public static  CommonViewHolder getViewHolder(View itemView, ViewGroup parent,int itemId){
        CommonViewHolder viewHolder;
        if (itemView == null){
            //获得对象
            Context context = parent.getContext();

            itemView = LayoutInflater.from(context).inflate(itemId,parent,false);
            viewHolder = new CommonViewHolder(itemView);
            viewHolder.setContext(context);
            itemView.setTag(viewHolder);
        }else{
            viewHolder = (CommonViewHolder) itemView.getTag();
        }
        return viewHolder;
    }
    //专门给RecyclerView使用的方法
    public static  CommonViewHolder getViewHolder(ViewGroup parent,int itemId){
        return getViewHolder(null,parent,itemId);
    }



    //返回行布局 get方法
    public View getItemView() {
        return itemView;
    }

    //*******************************
    //ViewHolder 设置数据的方法
    //这个类以下会在以后中不断丰富
    //设置文字
    //之所以写return this 会在adapter设置数据的时候可以一直点(链式编程)
    public CommonViewHolder setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }

    public CommonViewHolder setImage(int id,String url){
        ImageView imageView = getView(id);
        //// TODO: 16/11/23 请求图片 
        return this;
    }

    //recyclerView的行布局点击
    public CommonViewHolder setItemClick(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }
    public CommonViewHolder setViewClick(int id, View.OnClickListener listener){
        getView(id).setOnClickListener(listener);
        return this;
    }


}
