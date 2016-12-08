package lanou.days.birth;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import lanou.days.R;
import lanou.days.base.BaseFragment;
import lanou.days.birth.tool.DBTool;
import lanou.days.birth.tool.MyApp;

/**
 * Created by machuang on 16/11/22.
 */
public class BirthFragment extends BaseFragment implements View.OnClickListener {
    public static final int REQUEST = 1;
    public static final int FRI_REQUEST = 3;
    private ImageView birthModify;
    private TextView birthdayTv;
    private TextView birthdayTime;
    private long days = 0;
    private TextView haveBornTv;
    private RelativeLayout rlFriends;
    private TextView friendsCount;
    private int count;
    private ImageView run;
    private MyBroadCast broadCast;
    private static TextView recentBir;
    private DBTool dbTool;
    private BmobUser bmobUser;


    @Override
    protected void initData() {
        Glide.with(getActivity()).load(R.mipmap.running).into(run);
        dbTool = new DBTool();
        dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
            @Override
            public void onQuery(ArrayList<UserBean> userBeen) {
                count = userBeen.size();
                friendsCount.setText(String.valueOf(count));
            }
        });

        setItemOnClick(this, birthModify, rlFriends, recentBir);
        broadCast = new MyBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("haha");
        getActivity().registerReceiver(broadCast, filter);
        bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            dbTool.queryAllData(MyBean.class, new DBTool.OnQueryListener<MyBean>() {
                @Override
                public void onQuery(ArrayList<MyBean> myBeen) {
                    for (MyBean bean : myBeen
                            ) {
                        if (bean.getName().toString().equals(bmobUser.getUsername().toString())) {
                            birthdayTv.setText(bean.getBir().toString());
                            getBirthday();
                            birthdayTime.setText((int) days + "天");
                            getHaveBorn();
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void initView() {
        birthModify = bindView(R.id.iv_birth_modify);
        birthdayTv = bindView(R.id.tv_birthday);
        birthdayTime = bindView(R.id.tv_birthday_time);
        haveBornTv = bindView(R.id.tv_days);
        rlFriends = bindView(R.id.rl_friends);
        friendsCount = bindView(R.id.tv_friends);
        run = bindView(R.id.im_run);
        recentBir = bindView(R.id.tv_recent_name);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_birth;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_birth_modify:
                if (bmobUser == null) {
                    Toast.makeText(getActivity(), "亲,请先登录!", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    Intent intent = new Intent(MyApp.getContext(), ModifyBirthDayActivity.class);
                    startActivityForResult(intent, REQUEST);
                    break;
                }
            case R.id.rl_friends:
                Intent intent1 = new Intent(MyApp.getContext(), FriendsActivity.class);

                startActivity(intent1);
                break;
            case R.id.tv_recent_name:
                Calendar calendar = Calendar.getInstance();
                final String day = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
                        calendar.get(Calendar.DAY_OF_MONTH);
                final ArrayList<String> arrayList = new ArrayList<>();
                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> userBeen) {
                        for (UserBean bean : userBeen
                                ) {
                            if (day.equals(bean.getDate())) {
                                arrayList.add(bean.getName());
                            }
                        }
                        Intent intent = new Intent();
                        intent.setAction("hehe");
                        intent.putStringArrayListExtra("briName", arrayList);
                        getActivity().sendBroadcast(intent);

                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode && ModifyBirthDayActivity.RESULT == resultCode) {

            dbTool.queryAllData(MyBean.class, new DBTool.OnQueryListener<MyBean>() {
                @Override
                public void onQuery(ArrayList<MyBean> myBeen) {
                    for (MyBean bean : myBeen
                            ) {
                        if (bean.getName().toString().equals(bmobUser.getUsername().toString())) {
                            birthdayTv.setText(bean.getBir().toString());
                            getBirthday();
                            birthdayTime.setText((int) days + "天");
                            getHaveBorn();
                        }
                    }
                }
            });
        }


    }


    public void getBirthday() {
        String birthday = birthdayTv.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);//获取当前年份
        try {
            calendar.setTime(format.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int birthyear = calendar.get(Calendar.YEAR);
        while (birthyear < yearNow) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            birthyear = calendar.get(Calendar.YEAR);
        }
        Date ed = new Date();
        Date sd = calendar.getTime();

        if ((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000) < 0) {
            days = -((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000)) + 1;

        } else {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            sd = calendar.getTime();
            days = -((ed.getTime() - sd.getTime()) / (3600 * 24 * 1000)) + 1;

        }

    }

    public void getHaveBorn() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateBorn = format.parse(birthdayTv.getText().toString());
            Date dateNow = new Date(System.currentTimeMillis());

            int haveBorn = (int) ((dateNow.getTime() - dateBorn.getTime()) / (1000 * 3600 * 24));
            Log.d("BirthFragment", "haveBorn:" + haveBorn);
            haveBornTv.setText(haveBorn + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            friendsCount.setText(intent.getStringExtra("count"));

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadCast);
    }

    public static class MyRecentBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = "";
            for (String str : intent.getStringArrayListExtra("briName")
                    ) {
                name = name + str + ",";
            }
            if (name.equals("")) {
                recentBir.setText("今天没有人过生日!");
            } else {
                recentBir.setText(name + "过生日啦!");
            }
        }
    }
}
