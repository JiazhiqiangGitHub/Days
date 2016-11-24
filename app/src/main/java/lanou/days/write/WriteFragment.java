package lanou.days.write;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.days.R;
import lanou.days.base.BaseFragment;

/**
 * Created by dllo on 16/11/22.
 */
public class WriteFragment extends BaseFragment implements View.OnClickListener {
    private EditText title,content;
    private LinearLayout updata;
    @Override
    protected void initData() {
        //TODO 封装
        SharedPreferences getSp = getActivity().getSharedPreferences("write",Context.MODE_PRIVATE);
        title.setText(getSp.getString("文章标题",""));
        content.setText(getSp.getString("文章内容",""));

    }

    @Override
    protected void initView() {
        title = bindView(R.id.et_write_title);
        content = bindView(R.id.et_write_content);
        updata = bindView(R.id.ll_write_updata);
        updata.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_write;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_write_updata:
                writeDialog();
                break;
            default:
                break;
        }
    }

    private void writeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("确定发送喵?")
                .setIcon(R.mipmap.days_icon)
                .setMessage("您已经确定写完了喵?\n上传后可就会清空了喵");
        builder.setPositiveButton("上传", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (title.getText().toString().equals("")){
                    Toast.makeText(getContext(), "至少得写一个标题喵", Toast.LENGTH_SHORT).show();
                } else {
                    upData();
                }
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

    /**
     * 用于上传的方法,先判断用户是否登录
     */
    private void upData() {
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if(bmobUser != null){
            // 允许用户使用应用
            // 继续上传
            WriteBean writeBean = new WriteBean();
            String titleStr = title.getText().toString();
            writeBean.setTitle(titleStr);
            String contentStr = content.getText().toString();
            writeBean.setContent(contentStr);
            writeBean.setAuthor(bmobUser);
            writeBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(getContext(), "笔记创建成功了喵", Toast.LENGTH_SHORT).show();
                        title.getText().clear();
                        content.getText().clear();
                    } else {
                        Toast.makeText(getContext(), "失败了喵", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            //缓存用户对象为空时， 可打开用户注册界面
            Toast.makeText(getContext(), "您还没有登录喵,请先登录喵", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
     
        SharedPreferences sp = getActivity().getSharedPreferences("write", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("文章标题",title.getText().toString());
        editor.putString("文章内容",content.getText().toString());
        editor.apply();
           super.onDestroyView();
    }
}
