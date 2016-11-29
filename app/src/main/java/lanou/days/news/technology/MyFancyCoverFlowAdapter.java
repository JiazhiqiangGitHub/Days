package lanou.days.news.technology;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;

import java.util.List;

import lanou.days.R;

/**
 * Created by 贾志强 on 16/11/29.
 */
public class MyFancyCoverFlowAdapter extends FancyCoverFlowAdapter{
    private Context context;
    public List<Item> list;

    public MyFancyCoverFlowAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getCoverFlowItem(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.fragment_technology_head_item,null);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            convertView.setLayoutParams(new FancyCoverFlow.
                    LayoutParams(width/3,FancyCoverFlow.LayoutParams.WRAP_CONTENT));
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i % list.size());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
