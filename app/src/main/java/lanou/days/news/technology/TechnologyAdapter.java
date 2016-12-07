package lanou.days.news.technology;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.bean.TechnologyBean;

/**
 * Created by 贾志强 on 16/11/28.
 */

public class TechnologyAdapter extends BaseAdapter{
    private TechnologyBean bean;
    private Context context;

    public TechnologyAdapter(Context context) {
        this.context = context;
    }

    public void setBean(TechnologyBean bean) {
        Log.d("TechnologyAdapter", "bean:" + bean);
        Log.d("TechnologyAdapter", "htgftuyyu");
        this.bean = bean;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return bean.getData().getItems().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getItems().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.fragment_technology_item);
        viewHolder.setText(R.id.tv_technology_title,bean.getData().getItems().get(i).getTitle()).
                setText(R.id.tv_technology_body,bean.getData().getItems().get(i).getIntroduction()).
                setImage(R.id.iv_technology_read,bean.getData().getItems().get(i).getCover_image_url()).
                setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "i:" + i, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,TechnologyActivity.class);
                        intent.putExtra("technology",bean.getData().getItems().get(i).getUrl());
                        context.startActivity(intent);
                    }
                });


        return viewHolder.getItemView();
    }
}
