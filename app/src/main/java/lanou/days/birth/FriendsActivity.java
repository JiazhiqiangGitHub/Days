package lanou.days.birth;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import cn.bmob.v3.b.I;
import lanou.days.R;
import lanou.days.birth.tool.DBTool;
import lanou.days.birth.tool.OnRecyclerItemClickListener;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by dllo on 16/11/23.
 */
public class FriendsActivity extends BaseSwipeActivity implements View.OnClickListener, OnRecyclerItemClickListener {

    private ImageView addIv;
    private TextView back;
    private ImageView more;
    private PopupWindow popupWindow;
    private View pop;
    private RecyclerView moreRv;
    private ArrayList<String> arrayList;
    private PopAdapter adapter;
    private LinearLayoutManager manager;
    private StickyListHeadersListView stickyListHeadersListView;
    private MyConstellationAdapter conAdapter;
    private UserBean bean;
    private DBTool dbTool;
    private String name = null;
    private String constellation = null;
    private int kind;
    private String date;
    private int monthKind;
    private MyMonthAdapter monAdapter;
    private int birKind;
    private BirCountDownAdapter birAdapter;
    public static final int RESULT = 2;
    private int num;


    @Override
    protected int getLayout() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initViews() {
        addIv = bindView(R.id.iv_add);
        back = bindView(R.id.tv_back);
        more = bindView(R.id.iv_more);
        stickyListHeadersListView = bindView(R.id.slhl_list);
        pop = LayoutInflater.from(this).inflate(R.layout.pop, null);
        moreRv = (RecyclerView) pop.findViewById(R.id.rv_more);
    }

    @Override
    protected void initData() {
        setClick(this, addIv, back, more);
        arrayList = new ArrayList<>();
        arrayList.add("生日倒数");
        arrayList.add("月份");
        arrayList.add("星座");
        manager = new LinearLayoutManager(this);
        conAdapter = new MyConstellationAdapter(this);
        monAdapter = new MyMonthAdapter(this);
        birAdapter = new BirCountDownAdapter(this);
        dbTool = new DBTool();
        initMorePop();
        dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
            @Override
            public void onQuery(ArrayList<UserBean> userBeen) {
                monAdapter.setArrayList(userBeen);
                stickyListHeadersListView.setAdapter(monAdapter);
            }
        });
        stickyListHeadersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> userBeen) {
                        Intent intent = new Intent(FriendsActivity.this, DetailsActivity.class);
                        intent.putExtra("name",userBeen.get(i).getName());
                        intent.putExtra("constellation",userBeen.get(i).getConstellation());
                        intent.putExtra("date",userBeen.get(i).getDate());
                        intent.putExtra("id",userBeen.get(i).getId());
                        startActivity(intent);
                    }
                });

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        name = intent.getStringExtra("name");
        constellation = intent.getStringExtra("constellation");
        kind = intent.getIntExtra("kind", 0);
        date = intent.getStringExtra("date");
        monthKind = intent.getIntExtra("monthKind", 0);
        bean = new UserBean();
        bean.setKind(kind);
        bean.setName(name);
        bean.setDate(date);
        bean.setConstellation(constellation);
        bean.setDate(date);
        bean.setMonKind(monthKind);
        bean.setMonth(String.valueOf(monthKind));
        dbTool.insert(bean);
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                startActivity(new Intent(this, AddFriendsActivity.class));
                break;
            case R.id.tv_back:
                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> userBeen) {
                        Intent intent = new Intent();
                        int count = userBeen.size();
                        Log.d("FriendsActivity", "count:" + count);
                        intent.putExtra("count", count);
                        setResult(RESULT, intent);
                        finish();
                    }
                });

                break;
            case R.id.iv_more:
                if (!popupWindow.isShowing()) {
                    popupWindow.showAsDropDown(more);
                } else {
                    popupWindow.dismiss();
                }
                break;
        }
    }

    public void initMorePop() {
        popupWindow = new PopupWindow(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(pop);
        adapter = new PopAdapter();
        Log.d("FriendsActivity", "arrayList:" + arrayList);
        adapter.setArrayList(arrayList);
        moreRv.setAdapter(adapter);
        moreRv.setLayoutManager(manager);
        adapter.setOnRecyclerItemClickListener(this);

    }

    @Override
    public void onItemClick(final int position) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        switch (adapter.arrayList.get(position)) {
            case "生日倒数":
                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> userBeen) {
                        for (UserBean str : userBeen) {
                            num = getBirthdayCountDown(str.getDate());
                            str.setBirKind(num);
                            dbTool.upData(str);
                        }
                        dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                            @Override
                            public void onQuery(ArrayList<UserBean> userBeen) {
                                //TODO 根据日期 确定kind值
                                Collections.sort(userBeen, new Comparator<UserBean>() {
                                    @Override
                                    public int compare(UserBean userBean, UserBean t1) {
                                        return userBean.getBirKind() - t1.getBirKind();
                                    }
                                });
                                Log.d("FriendsActivity", "userBeen.get(position).getBirKind():" + userBeen.get(position).getBirKind());
                                birAdapter.setArrayList(userBeen);
                                stickyListHeadersListView.setAdapter(birAdapter);
                            }
                        });

                    }
                });


//                stickyListHeadersListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(AbsListView absListView, int i) {
//                       // birAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//                    }
//                });
                break;
            case "月份":
                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> userBeen) {
                        Collections.sort(userBeen, new Comparator<UserBean>() {
                            @Override
                            public int compare(UserBean userBean, UserBean t1) {
                                return userBean.getMonKind() - t1.getMonKind();
                            }
                        });
                        Log.d("FriendsActivity", "userBeen:" + userBeen);
                        monAdapter.setArrayList(userBeen);
                        stickyListHeadersListView.setAdapter(monAdapter);
                    }
                });
//                stickyListHeadersListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(AbsListView absListView, int i) {
//                        monAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//                    }
//                });
                break;
            case "星座":
//                dbTool.deleteAllData(UserBean.class);
                dbTool.queryAllData(UserBean.class, new DBTool.OnQueryListener<UserBean>() {
                    @Override
                    public void onQuery(ArrayList<UserBean> constellationBeen) {
                        Collections.sort(constellationBeen, new Comparator<UserBean>() {
                            @Override
                            public int compare(UserBean constellationBean, UserBean t1) {
                                return constellationBean.getKind() - t1.getKind();
                            }
                        });//分组
                        conAdapter.setArrayList(constellationBeen);
                        stickyListHeadersListView.setAdapter(conAdapter);
                    }
                });
//                stickyListHeadersListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(AbsListView absListView, int i) {
////                        stickyListHeadersListView.setSelection(i);
//                        conAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//                    }
//                });
                break;


        }
    }

    public int getBirthdayCountDown(String date) {
        int birKind;
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
        if (days <= 31) {
            birKind = 1;
        } else {
            birKind = 2;
        }
        return birKind;
    }
}
