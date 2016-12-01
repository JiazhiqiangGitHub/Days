package lanou.days.birth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lanou.days.R;
import lanou.days.base.BaseActivity;
import lanou.days.birth.tool.DBTool;

/**
 * Created by dllo on 16/11/30.
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView date;
    private TextView constellation;
    private TextView countDown;
    private TextView name;
    private ImageView delete;
    private DBTool dbTool;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.iv_details_back);
        date = bindView(R.id.tv_details_birthday);
        constellation = bindView(R.id.tv_details_cons);
        countDown = bindView(R.id.tv_count_down);
        name = bindView(R.id.details_name);
        delete = bindView(R.id.iv_delete);

    }

    @Override
    protected void initData() {
        dbTool = new DBTool();
        date.setText(getIntent().getStringExtra("date"));
        constellation.setText(getIntent().getStringExtra("constellation"));
        name.setText(getIntent().getStringExtra("name"));
        countDown.setText(String.valueOf(getBirthdayCountDown(getIntent().getStringExtra("date"))));
        setClick(this, back, delete);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_details_back:
                onBackPressed();
                break;
            case R.id.iv_delete:
                isDelete(getIntent().getIntExtra("id",-1));
                break;
        }
    }

    public long getBirthdayCountDown(String date) {
        long days;
        String birthday = date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);//获取当前年份
        try {
            calendar.setTime(format.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int birthYear = calendar.get(Calendar.YEAR);
        while (birthYear < yearNow) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            birthYear = calendar.get(Calendar.YEAR);
        }
        Date ed = new Date();
        Log.d("AddFriendsActivity", "ed:" + ed);
        Date sd = calendar.getTime();
        Log.d("AddFriendsActivity", "sd:" + sd);

        if ((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000) < 0) {
            days = -((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000)) + 1;
            Log.d("AddFriendsActivity", "days:if" + days);

        } else {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            sd = calendar.getTime();
            days = -((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000)) + 1;
            Log.d("AddFriendsActivity", "days:else" + days);
        }

        return days;
    }

    public void isDelete(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        builder.setTitle("是否删除?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbTool.deleteById(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
