package lanou.days.birth;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/24.
 */

public class AddFriendsActivity extends BaseSwipeActivity implements View.OnClickListener {

    private TextView contactBirthday;
    private int nYear;
    private int nMonth;
    private int nDay;
    private ImageView addIv;
    private int mYear;
    private int mMonth;
    private int mDay;
    private EditText userName;



    @Override
    protected int getLayout() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void initViews() {
        contactBirthday = bindView(R.id.tv_contact_birthday);
        addIv = bindView(R.id.iv_add);
        userName = bindView(R.id.edt_user_name);
    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        nYear = calendar.get(Calendar.YEAR);
        nMonth = calendar.get(Calendar.MONTH);
        nDay = calendar.get(Calendar.DAY_OF_MONTH);
        contactBirthday.setText(nYear + "-" + (nMonth + 1) + "-" + nDay);
        setClick(this, contactBirthday, addIv);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_contact_birthday:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear + 1;
                        mDay = dayOfMonth;
                        contactBirthday.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, nYear, nMonth, nDay).show();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(AddFriendsActivity.this, FriendsActivity.class);
                if (mMonth != 0){
                    intent.putExtra("name", userName.getText().toString());
                    intent.putExtra("constellation", getConstellation(mMonth, mDay).toString());
                    intent.putExtra("kind", getKind(getConstellation(mMonth, mDay)));
                    intent.putExtra("date", contactBirthday.getText());
                    intent.putExtra("monthKind", getMonthKind(mMonth));
                    intent.putExtra("countDown",getBirthdayCountDown());
                }else {
                    intent.putExtra("name", userName.getText().toString());
                    intent.putExtra("constellation", getConstellation(nMonth+1, nDay).toString());
                    intent.putExtra("kind", getKind(getConstellation(nMonth+1, nDay)));
                    intent.putExtra("date", contactBirthday.getText());
                    intent.putExtra("monthKind", getMonthKind(nMonth+1));
                    intent.putExtra("countDown",getBirthdayCountDown());
                }
                startActivity(intent);
                finish();
                break;
        }
    }

    public int getBirthdayCountDown() {
        int birKind;
        long days ;
        String birthday = contactBirthday.getText().toString();
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
        if (days <= 31) {
            birKind = 1;
        } else {
            birKind = 2;
        }
        return birKind;
    }


    public int getMonthKind(int month) {
        int monKind;
        if (month != 0) {
            monKind = month;
        } else {
            monKind = nMonth + 1;
        }
        return monKind;
    }

    public int getKind(String constellation) {
        int kind;
        if (constellation.equals("白羊座")) {
            kind = 1;
        } else if (constellation.equals("金牛座")) {
            kind = 2;
        } else if (constellation.equals("双子座")) {
            kind = 3;
        } else if (constellation.equals("巨蟹座")) {
            kind = 4;
        } else if (constellation.equals("狮子座")) {
            kind = 5;
        } else if (constellation.equals("处女座")) {
            kind = 6;
        } else if (constellation.equals("天秤座")) {
            kind = 7;
        } else if (constellation.equals("天蝎座")) {
            kind = 8;
        } else if (constellation.equals("射手座")) {
            kind = 9;
        } else if (constellation.equals("摩羯座")) {
            kind = 10;
        } else if (constellation.equals("水瓶座")) {
            kind = 11;
        } else if (constellation.equals("双鱼座")) {
            kind = 12;
        } else {
            kind = 0;
        }
        return kind;
    }

    public String getConstellation(int month, int days) {
        String constellation = null;

            if (3 == month && days >= 21 && days <= 31) {
                constellation = "白羊座";
            } else if (4 == month && days >= 1 && days <= 20) {
                constellation = "白羊座";
            } else if (4 == month && days >= 21 && days <= 30) {
                constellation = "金牛座";
            } else if (5 == month && days >= 1 && days <= 21) {
                constellation = "金牛座";
            } else if (5 == month && days >= 22 && days <= 31) {
                constellation = "双子座";
            } else if (6 == month && days >= 1 && days <= 21) {
                constellation = "双子座";
            } else if (6 == month && days >= 22 && days <= 30) {
                constellation = "巨蟹座";
            } else if (7 == month && days >= 1 && days <= 22) {
                constellation = "巨蟹座";
            } else if (7 == month && days >= 23 && days <= 31) {
                constellation = "狮子座";
            } else if (8 == month && days >= 1 && days <= 23) {
                constellation = "狮子座";
            } else if (8 == month && days >= 24 && days <= 31) {
                constellation = "处女座";
            } else if (9 == month && days >= 1 && days <= 23) {
                constellation = "处女座";
            } else if (9 == month && days >= 24 && days <= 30) {
                constellation = "天秤座";
            } else if (10 == month && days >= 1 && days <= 23) {
                constellation = "天秤座";
            } else if (10 == month && days >= 24 && days <= 31) {
                constellation = "天蝎座";
            } else if (11 == month && days >= 1 && days <= 22) {
                constellation = "天蝎座";
            } else if (11 == month && days >= 23 && days <= 30) {
                constellation = "射手座";
            } else if (12 == month && days >= 1 && days <= 21) {
                constellation = "射手座";
            } else if (12 == month && days >= 22 && days <= 31) {
                constellation = "摩羯座";
            } else if (1 == month && days >= 1 && days <= 20) {
                constellation = "摩羯座";
            } else if (1 == month && days >= 21 && days <= 31) {
                constellation = "水瓶座";
                return constellation;
            } else if (2 == month && days >= 1 && days <= 19) {
                constellation = "水瓶座";
            } else if (2 == month && days >= 20 && days <= 30) {
                constellation = "双鱼座";
            } else {
                constellation = "双鱼座";
            }
        return constellation;
    }
}
