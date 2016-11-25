package lanou.days.news.read;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.bean.ReadBean;

/**
 * Created by 贾志强 on 16/11/25.
 */
public class ReadAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private ReadBean bean;

    public void setBean(ReadBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.fragment_news_read_item);



    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setText(R.id.tv_news_read_title,bean.getData().getList().get(position).getShortTitle()).
                setText(R.id.tv_news_read_body,bean.getData().getList().get(position).getTitle()).
                setImage(R.id.iv_news_read,bean.getData().getList().get(position).getPic());

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        Log.d("ReadAdapter", "bean:" + bean);
        int count  = 0;
        try {
            count = bean.getData().getList().size();
        }catch (Exception e){}

        return count;
    }


}
