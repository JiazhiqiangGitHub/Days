package lanou.days.birth;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import cn.bmob.v3.BmobUser;
import lanou.days.R;
import lanou.days.birth.tool.DBTool;

/**
 * Created by dllo on 16/11/22.
 */
public class ModifyBirthDayActivity extends BaseSwipeActivity implements View.OnClickListener {

    private Button modifyBtn;
    private Button chooseBtn;
    private int year;
    private int month;
    private int day;
    private EditText show;
    public static final int RESULT = 4;
    private MyBean myBean;
    private BmobUser bmobUser;

    @Override
    protected void initData() {
        myBean = new MyBean();
        bmobUser = BmobUser.getCurrentUser();
       setClick(this,modifyBtn,chooseBtn);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initViews() {
        modifyBtn = bindView(R.id.btn_modify);
        chooseBtn = bindView(R.id.btn_choose);
        show = bindView(R.id.edt_show);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_choose:
                Intent intent = new Intent();
                myBean.setName(bmobUser.getUsername().toString());
                myBean.setBir(show.getText().toString());
                Log.d("ModifyBirthDayActivity", "show.getText():" + show.getText());
                DBTool dbTool = new DBTool();
                dbTool.insert(myBean);
                dbTool.upData(myBean);
                String str = String.valueOf(show.getText());
                intent.putExtra("birthday",str);
                setResult(RESULT,intent);
                finish();
                break;
            case R.id.btn_modify:
                //获取当前的日期
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        show.setText(year + "-" +(monthOfYear + 1)+ "-" + dayOfMonth );

                    }
                }, year, month, day).show();

                break;
        }
    }
}
