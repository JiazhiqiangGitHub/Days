package lanou.days;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import lanou.days.base.BaseActivity;
import lanou.days.birth.BirthFragment;
import lanou.days.enter.LoginActivity;
import lanou.days.news.NewsFragment;
import lanou.days.note.NoteFragment;
import lanou.days.setting.SettingFragment;
import lanou.days.write.WriteFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private RadioButton btnWrite,btnNote,btnBirth,btnSetting,btnNews;
    private FragmentManager manager;
    private TextView tvName;
    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_drawer;
    }

    @Override
    protected void initViews() {
        btnWrite = bindView(R.id.btn_main_write);
        btnNote = bindView(R.id.btn_main_note);
        btnBirth = bindView(R.id.btn_main_birth);
        btnSetting = bindView(R.id.btn_main_setting);
        btnNews = bindView(R.id.btn_main_news);
        
        NavigationView v = bindView(R.id.main_nv);
        View headerView = v.getHeaderView(0);
        tvName = bindView(headerView,R.id.tv_main_user_name);

    }

    @Override
    protected void initData() {

        initDrawerLayout();

        btnWrite.setChecked(true);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.lb_main, new WriteFragment());
        transaction.commit();

        btnWrite.setOnClickListener(this);
        btnNote.setOnClickListener(this);
        btnBirth.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnNews.setOnClickListener(this);

        BmobUser bmobUser = BmobUser.getCurrentUser();
        Log.d("aaMainActivity", "bmobUser:" + bmobUser);
        Log.d("aaMainActivity", "tvName:" + tvName);
            if (bmobUser != null) {
                Log.d("Sysout", bmobUser.getUsername());
                tvName.setText(bmobUser.getUsername());
            }
    }

    //创建侧滑
    private void initDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nv);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
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


    }

    //侧滑中item的点击
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_mine){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_close){
            BmobUser user = BmobUser.getCurrentUser();
            if (user != null) {
                BmobUser.logOut();
                Toast.makeText(this, "已退出", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "你根本没有登录", Toast.LENGTH_SHORT).show();
            }
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }





}
