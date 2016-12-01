package lanou.days.birth;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.birth.tool.OnRecyclerItemClickListener;

/**
 * Created by dllo on 16/11/24.
 */
public class PopAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    ArrayList<String> arrayList;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.item_pop);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setText(R.id.tv_item_pop,arrayList.get(position));
        holder.setViewClick(R.id.ll_item_pop, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }
}
