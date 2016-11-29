package lanou.days.birth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.BaseActivity;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dllo on 16/11/28.
 */

public class MyMonthAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context context;
    private ArrayList<UserBean> arrayList;

    public MyMonthAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<UserBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder headViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_month_head,parent,false);
            headViewHolder =new  HeadViewHolder(convertView);
            convertView.setTag(headViewHolder);
        }else {
            headViewHolder = (HeadViewHolder) convertView.getTag();
        }
        headViewHolder.monHead.setText(arrayList.get(position).getMonth() + "月");
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(String.valueOf(this.arrayList.get(position).getMonKind()) );
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
        BodyViewHolder bodyViewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_month_body,viewGroup,false);
            bodyViewHolder = new BodyViewHolder(view);
            view.setTag(bodyViewHolder);
        }else {
            bodyViewHolder = (BodyViewHolder) view.getTag();
        }
        UserBean bean = arrayList.get(i);
        if (bean != null){
            bodyViewHolder.monName.setText(arrayList.get(i).getName());
            bodyViewHolder.monDate.setText(arrayList.get(i).getDate());
        }
        return view;
    }

    private class HeadViewHolder {

        private final TextView monHead;

        public HeadViewHolder(View convertView) {
            monHead = (TextView) convertView.findViewById(R.id.tv_month_head);
        }
    }

    private class BodyViewHolder {

        private final TextView monName;
        private final TextView monDate;

        public BodyViewHolder(View view) {
            monName = (TextView) view.findViewById(R.id.tv_month_name);
            monDate = (TextView) view.findViewById(R.id.tv_month_date);
        }
    }
}
