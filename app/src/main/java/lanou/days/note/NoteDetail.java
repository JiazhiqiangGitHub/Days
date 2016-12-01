package lanou.days.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import lanou.days.R;
import lanou.days.base.BaseActivity;
import lanou.days.write.WriteBean;

import static java.security.AccessController.getContext;

/**
 * Created by 张家鑫 on 16/11/26.
 */
public class NoteDetail extends BaseActivity implements View.OnClickListener {
    private EditText title,time,content;
    private LinearLayout delete,rewrite,save;
    private ImageView back;
    private String mId;

    @Override
    protected int getLayout() {
        return R.layout.activity_note_detail;
    }

    @Override
    protected void initViews() {
        title = bindView(R.id.et_note_detail_title);
        time = bindView(R.id.et_note_detail_time);
        content = bindView(R.id.et_note_detail_content);
        delete = bindView(R.id.ll_note_detail_delete);
        rewrite = bindView(R.id.ll_note_detail_rewrite);
        back = bindView(R.id.iv_note_detail_back);
        save = bindView(R.id.ll_note_detail_save);
        title.setEnabled(false);
        time.setEnabled(false);
        content.setEnabled(false);
        setClick(this,delete,rewrite,back,save);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        time.setText(intent.getStringExtra("time"));
        content.setText(intent.getStringExtra("content"));
        mId = intent.getStringExtra("objectID");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_note_detail_delete:
                writeDialog();
                break;
            case R.id.ll_note_detail_rewrite:
                toRewrite();
                break;
            case R.id.iv_note_detail_back:
                finish();
                break;
            case R.id.ll_note_detail_save:
                toSave();
                break;
        }
    }

    private void toSave() {
        WriteBean writeBean = new WriteBean();
        writeBean.setValue("title",title.getText().toString());
        writeBean.setValue("time",time.getText().toString());
        writeBean.setValue("content",content.getText().toString());
        writeBean.update(mId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(NoteDetail.this, "更新成功了喵", Toast.LENGTH_SHORT).show();
                    save.setVisibility(View.INVISIBLE);
                    title.setEnabled(false);
                    time.setEnabled(false);
                    content.setEnabled(false);
                    title.setTextColor(0xFF737576);
                    time.setTextColor(0xFF737576);
                    content.setTextColor(0xFF737576);
                } else {
                    Toast.makeText(NoteDetail.this, "失...失败了喵", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void toRewrite() {
        title.setEnabled(true);
        time.setEnabled(true);
        content.setEnabled(true);
        title.setTextColor(Color.BLACK);
        time.setTextColor(Color.BLACK);
        content.setTextColor(Color.BLACK);
        save.setVisibility(View.VISIBLE);
    }

    private void writeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("确定删除喵?")
                .setIcon(R.mipmap.days_icon)
                .setMessage("您确定不需要这篇了喵?\n删除后可就没有了喵");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                WriteBean writeBean1 = new WriteBean();
                writeBean1.setObjectId(mId);
                writeBean1.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(NoteDetail.this, "成功删除了", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(NoteDetail.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        builder.setNegativeButton("先等等", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 什么也不做,\(^o^)/~
            }
        });
        builder.show();
    }


}
