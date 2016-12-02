package lanou.days.note;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.write.WriteBean;

/**
 * 笔记Fragment的Adapter
 * Created by dllo on 16/11/23.
 */

public class NoteAdapter extends BaseAdapter {

    List<WriteBean> mList;

    public void setList(List<WriteBean> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.item_note);
        viewHolder.setText(R.id.tv_note_item_title,mList.get(i).getTitle())
                .setText(R.id.tv_note_item_time,mList.get(i).getUpdatedAt())
                .setText(R.id.tv_note_item_content,mList.get(i).getContent());
        return viewHolder.getItemView();
    }

    public String[] getText(int i) {
        String title = mList.get(i).getTitle();
        String time = mList.get(i).getUpdatedAt();
        String content = mList.get(i).getContent();
        String objectID = mList.get(i).getObjectId();
        String[] all = {title,time,content,objectID};
        return all;
    }



}
