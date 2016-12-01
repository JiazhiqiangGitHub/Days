package lanou.days.note;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import lanou.days.R;
import lanou.days.base.CommonViewHolder;
import lanou.days.write.WriteBean;

/**
 * Created by 张家鑫 on 16/12/1.
 */

public class NoteDirectoryAdapter extends BaseAdapter {
   String[] mList = {"账单","日记","记录","其他"};

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.item_note_directory);
        viewHolder.setText(R.id.tv_note_main_type,mList[i]);
        return viewHolder.getItemView();
    }
}
