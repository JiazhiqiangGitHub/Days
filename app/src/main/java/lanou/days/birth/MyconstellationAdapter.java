package lanou.days.birth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.days.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dllo on 16/11/26.
 */
public class MyConstellationAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    private ArrayList<UserBean> arrayList;
    private Context context;

    public MyConstellationAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<UserBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(String.valueOf(this.arrayList.get(position).getKind()));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BodyViewHolder viewHolder ;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_body,viewGroup,false);
            viewHolder = new BodyViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (BodyViewHolder) view.getTag();
        }
        UserBean bean = this.arrayList.get(i);
        if (bean != null){
            viewHolder.userName.setText(arrayList.get(i).getName());
            viewHolder.dateTv.setText(arrayList.get(i).getDate());
        }
        return view;
    }
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder headViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_head,parent,false);
            headViewHolder = new HeadViewHolder(convertView);
            convertView.setTag(headViewHolder);
        }else {
            headViewHolder = (HeadViewHolder) convertView.getTag();
        }
        String headText = this.arrayList.get(position).getConstellation();
        headViewHolder.headTv.setText(headText);

        return convertView;
    }

    private class BodyViewHolder {

        private  TextView userName;
        private  TextView dateTv;

        public BodyViewHolder(View view) {
            userName = (TextView) view.findViewById(R.id.tv_user_name);
            dateTv = (TextView) view.findViewById(R.id.tv_date);
        }
    }

    private class HeadViewHolder {

        private  TextView headTv;


        public HeadViewHolder(View convertView) {
            headTv = (TextView) convertView.findViewById(R.id.tv_head);

        }
    }
}
