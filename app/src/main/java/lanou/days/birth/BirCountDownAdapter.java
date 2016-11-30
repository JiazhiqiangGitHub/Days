package lanou.days.birth;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.days.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dllo on 16/11/29.
 */

public class BirCountDownAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    private ArrayList<UserBean> arrayList;
    private Context context;

    public BirCountDownAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<UserBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder headViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bir_head,parent,false);
            headViewHolder = new HeadViewHolder(convertView);
            convertView.setTag(headViewHolder);
        }else {
            headViewHolder = (HeadViewHolder) convertView.getTag();
        }
        if (arrayList.get(position).getBirKind() == 1){
            headViewHolder.birCountDown.setText("一个月之内");
        }else {
            headViewHolder.birCountDown.setText("超过一个月");
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(String.valueOf(this.arrayList.get(position).getBirKind()));

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
            view = LayoutInflater.from(context).inflate(R.layout.item_bir_body,viewGroup,false);
            bodyViewHolder = new BodyViewHolder(view);
            view.setTag(bodyViewHolder);
        }else {
            bodyViewHolder = (BodyViewHolder) view.getTag();
        }
        bodyViewHolder.name.setText(arrayList.get(i).getName());
        bodyViewHolder.date.setText(arrayList.get(i).getDate());
        return view;
    }

    private class HeadViewHolder {

        private  TextView birCountDown;

        public HeadViewHolder(View convertView) {
            birCountDown = (TextView) convertView.findViewById(R.id.tv_bir_head);
        }
    }

    private class BodyViewHolder {

        private  TextView name;
        private  TextView date;

        public BodyViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.tv_bir_name);
            date = (TextView) view.findViewById(R.id.tv_bir_date);
        }
    }
}
