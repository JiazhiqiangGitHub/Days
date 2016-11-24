package lanou.days.note;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;

/**
 * Created by dllo on 16/11/23.
 */

public class NoteAdapter extends BaseAdapter {
    ArrayList<String> mArrayList;
    // TODO 数据类Bean

    public void setArrayList(ArrayList<String> arrayList) {
        mArrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.item_note);
        viewHolder.setText(R.id.tv_note_item_title,"都是标题")
                .setText(R.id.tv_note_item_time,"2000-00-00")
                .setText(R.id.tv_note_item_content,"这是正文,我很严肃的说这是正文,到时候肯定会很长");
        return viewHolder.getItemView();

    }
}
