package lanou.days;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import de.hdodenhof.circleimageview.CircleImageView;
import lanou.days.base.BaseActivity;
import lanou.days.birth.BirthFragment;
import lanou.days.enter.LoginActivity;
import lanou.days.news.NewsFragment;
import lanou.days.note.NoteFragment;
import lanou.days.setting.SettingFragment;
import lanou.days.write.WriteFragment;

import static lanou.days.enter.LoginActivity.RESULTOne;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FragmentManager manager;
    private TextView tvName;
    private PlatformActionListener platformActionListener;
    private String qqName, icon, name, usericon;
    private CircleImageView userIcon;
    private BottomNavigationView btnNV;
    private String b;
    private String a;


    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_drawer;
    }

    @Override
    protected void initViews() {
        btnNV = bindView(R.id.btn_nv);

        NavigationView v = bindView(R.id.main_nv);
        View headerView = v.getHeaderView(0);
        tvName = bindView(headerView, R.id.tv_main_user_name);
        userIcon = bindView(headerView, R.id.iv_main_user_picture);
        userIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        initDrawerLayout();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.lb_main, new WriteFragment());
        transaction.commit();

        btnNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.btn_main_write:
                        transaction.replace(R.id.lb_main, new WriteFragment());
                        break;
                    case R.id.btn_main_note:

                        transaction.replace(R.id.lb_main, new NoteFragment());

                        break;
                    case R.id.btn_main_birth:

                        transaction.replace(R.id.lb_main, new BirthFragment());

                        break;
                    case R.id.btn_main_news:

                        transaction.replace(R.id.lb_main, new NewsFragment());

                        break;
                    case R.id.btn_main_setting:

                        transaction.replace(R.id.lb_main, new SettingFragment());

                        break;
                }
                transaction.commit();
                return true;
            }
        });

        //判断是否登录
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        try {

            PlatformDb platformDb = qq.getDb();
            qqName = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(qqName)) {
                tvName.setText(qqName);

                Picasso.with(this).load(icon).into(userIcon);

            }
        } catch (NullPointerException e) {

        }

        //再给一遍授权
        //输出所有授权信息
        platformActionListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //输出所有授权信息
                platform.getDb().exportData();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        };
    }

    //创建侧滑
    private void initDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nv);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //侧滑中item的点击
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_mine) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 1);
        } else if (id == R.id.nav_close) {

            BmobUser user = BmobUser.getCurrentUser();
            if (user != null) {
                BmobUser.logOut();
                Toast.makeText(this, "已退出", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_qqclose) {
            //退出登录
            Platform qq = ShareSDK.getPlatform(QQ.NAME);
            if (qq.isAuthValid()) {
                qq.removeAccount(true);
            } else {
                qq.setPlatformActionListener(platformActionListener);
                qq.authorize();//单独授权，OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                qq.removeAccount(true);
                setResult(-1);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            //退出登录
            return;
        }
        if (requestCode == 1 && LoginActivity.RESULT == resultCode && data != null) {
            qqName = data.getStringExtra("name");
            icon = data.getStringExtra("icon");
            tvName.setText("我的名字叫:" + qqName);
            Picasso.with(this).load(icon).into(userIcon);

        }
        if (requestCode == 1 && RESULTOne == resultCode && data != null) {
            name = data.getStringExtra("number");
            tvName.setText("我的名字叫:" + name);
        }

    }

    //头像点击事件
    @Override
    public void onClick(View view) {
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null){
        Intent intent = new Intent(this, UserIconActivity.class);
        startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BmobUser bmobUser = BmobUser.getCurrentUser();

        if (bmobUser != null) {
            a = bmobUser.getObjectId();
            Log.d("Sysout", bmobUser.getUsername());
            tvName.setText("我的名字是:" + bmobUser.getUsername());

            //// TODO: 16/12/8
            //获取bmob头像
            BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
            bmobQuery.addWhereEqualTo("objectId", a);
            bmobQuery.findObjects(new FindListener<MyUser>() {
                @Override
                public void done(List<MyUser> list, BmobException e) {
                    if (e == null) {
                        b = list.get(0).getIcon();
                        Log.d("MainActivity", b);
                        Picasso.with(getBaseContext()).load(b).into(userIcon);

                    }
                }
            });

        }else{

           return;
        }

    }
}
