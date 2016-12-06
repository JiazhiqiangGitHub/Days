package lanou.days.news.read;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import lanou.days.MainActivity;
import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.bean.ReadBean;

/**
 * Created by 贾志强 on 16/11/25.
 */
public class ReadAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private Context context;
    private ReadBean bean;



    public ReadAdapter(Context context) {
        this.context = context;
    }

    public void setBean(ReadBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.fragment_news_read_item);



    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setText(R.id.tv_news_read_title,bean.getData().getItems().get(position).getTitle()).
                setText(R.id.tv_news_read_body,bean.getData().getItems().get(position).getIntroduction()).
                setImage(R.id.iv_news_read,bean.getData().getItems().get(position).getCover_image_url()).
                setViewClick(R.id.btn_share_read, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //// TODO: 16/12/6 传值
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("readTitle",bean.getData().getItems().get(position).getTitle());
                        intent.putExtra("readBody",bean.getData().getItems().get(position).getIntroduction());
                        context.startActivity(intent);
                    }
                }).
                setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,ReadActivity.class);

                        intent.putExtra("read",bean.getData().getItems().get(position).getUrl());
                        context.startActivity(intent);
                    }
                });
                String str = bean.getData().getItems().get(position).getIntroduction();
                if (str.length() >= 80){
                    String strTwo = str.substring(0,80);
                    holder.setText(R.id.tv_news_read_body,strTwo+". . .");
                }

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
            count = bean.getData().getItems().size();
        }catch (Exception e){}

        return count;
    }

}
