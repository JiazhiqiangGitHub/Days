package lanou.days.birth;


import android.content.Intent;


import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.birth.tool.DBTool;
import lanou.days.birth.tool.MyApp;

/**
 * Created by machuang on 16/11/22.
 */
public class BirthFragment extends BaseFragment implements View.OnClickListener {
    public static int REQUEST = 1;
    private ImageView birthModify;
    private TextView birthdayTv;
    private TextView birthdayTime;
    private long days = 0;
    private TextView haveBornTv;
    private RelativeLayout rlFriends;
    private TextView friendsCount;
    private int count;


    @Override
    protected void initData() {
        DBTool dbTool = new DBTool();
        dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
            @Override
            public void onQuery(ArrayList<UserBean> userBeen) {
                count = userBeen.size();
                friendsCount.setText(String.valueOf(count));
            }
        });

        setItemOnClick(this, birthModify, rlFriends);

    }

    @Override
    protected void initView() {
        birthModify = bindView(R.id.iv_birth_modify);
        birthdayTv = bindView(R.id.tv_birthday);
        birthdayTime = bindView(R.id.tv_birthday_time);
        haveBornTv = bindView(R.id.tv_days);
        rlFriends = bindView(R.id.rl_friends);
        friendsCount = bindView(R.id.tv_friends);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_birth;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_birth_modify:
                Intent intent = new Intent(MyApp.getContext(),ModifyBirthDayActivity.class);
                startActivityForResult(intent,REQUEST);
                break;
            case R.id.rl_friends:
                Intent intent1 = new Intent(MyApp.getContext(),FriendsActivity.class);
                startActivityForResult(intent1,REQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && ModifyBirthDayActivity.RESULT == resultCode) {
            birthdayTv.setText(data.getStringExtra("birthday"));
            getBirthday();
            birthdayTime.setText((int) days + "天");
            getHaveBorn();
        }else if (requestCode == REQUEST && FriendsActivity.RESULT == resultCode){
            Log.d("BirthFragment", "data.getIntExtra" + data.getIntExtra("count", 0));
            friendsCount.setText(data.getIntExtra("count",0));
        }
    }



        public void getBirthday(){
            String birthday =  birthdayTv.getText().toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);//获取当前年份
            try {
                calendar.setTime(format.parse(birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        int birthyear = calendar.get(Calendar.YEAR);
        while (birthyear<yearNow){
            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
            birthyear = calendar.get(Calendar.YEAR);
        }
        Date ed = new Date();
        Date sd = calendar.getTime();

        if ((ed.getTime() - sd.getTime())/(3600*24*1000)<0){
            days = -((ed.getTime()- sd.getTime())/(3600*24*1000))+1;

        }else{
            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) + 1 );
            sd = calendar.getTime();
            days = -((ed.getTime() - sd.getTime())/(3600*24*1000))+1;

        }

    }
    public void getHaveBorn(){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateBorn = format.parse(birthdayTv.getText().toString());
            Date dateNow = new Date(System.currentTimeMillis());
//            long days = (int) (dateNow.getTime() - dateBorn.getTime());
            int haveBorn = (int) ((dateNow.getTime()-dateBorn.getTime())/(1000*3600*24));
            Log.d("BirthFragment", "haveBorn:" + haveBorn);
            haveBornTv.setText(haveBorn +"");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
