package lanou.days.birth;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/24.
 */

public class AddFriendsActivity extends BaseSwipeActivity implements View.OnClickListener {

    private TextView contactBirthday;
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        contactBirthday = (TextView) findViewById(R.id.tv_contact_birthday);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        contactBirthday.setText(year + "-" + month + "-" + day);
        contactBirthday.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_contact_birthday:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        contactBirthday.setText(year + "-" +(monthOfYear + 1)+ "-" + dayOfMonth );

                    }
                }, year, month, day).show();
                break;
        }
    }

}
