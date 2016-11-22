package lanou.days.birth;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/22.
 */
public class ModifyBirthDayActivity extends BaseActivity implements View.OnClickListener {

    private Button modifyBtn;
    private Button chooseBtn;
    private int year;
    private int month;
    private int day;
    private EditText show;
    public static int RESULT = 0;
    @Override
    protected void initData() {
       setClick(this,modifyBtn);
        setClick(this,chooseBtn);
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
                Log.d("ModifyBirthDayActivity", "show.getText():" + show.getText());
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
                        show.setText(year + "年" +(monthOfYear + 1)+ "月" + dayOfMonth + "日");

                    }
                }, year, month, day).show();
                break;
        }
    }
}
