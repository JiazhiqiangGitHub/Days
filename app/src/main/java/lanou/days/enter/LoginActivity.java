package lanou.days.enter;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/23.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etTelephone,etPassword;
    private ImageView btnBcak,qq;
    private Button btnEnter,btnNew;
    private String tel;
    public static  final int RESULT = 0;
    public static  final int RESULTOne = 1;
    private TextView tvName;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        etTelephone = bindView(R.id.et_enter_number);
        etPassword = bindView(R.id.et_enter_password);
        btnBcak = bindView(R.id.btn_enter_back);
        btnEnter = bindView(R.id.btn_enter_enter);
        btnNew = bindView(R.id.btn_enter_new);
        qq = bindView(R.id.qq);


    }

    @Override
    protected void initData() {
        btnBcak.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        //按钮变色
        EditTextChangeColor();
        qq.setOnClickListener(this);

    }

    private void EditTextChangeColor() {
        etTelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tel = etTelephone.getText().toString().trim();
                if (tel != null){
                    btnEnter.setEnabled(true);
                    btnEnter.setBackgroundColor(Color.BLUE);
                }else{
                    btnEnter.setEnabled(false);
                    btnEnter.setBackgroundColor(Color.GRAY);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter_back:
                finish();
                break;
            case R.id.btn_enter_enter:

                final String number = etTelephone.getText().toString();
                String world = etPassword.getText().toString();
                BmobUser myUser = new BmobUser();
                myUser.setUsername(number);
                myUser.setPassword(world);
                myUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
                            Intent intent = new Intent();
                            intent.putExtra("number", number);
                            setResult(RESULTOne, intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "欢迎回家,主人", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.btn_enter_new:
                Intent intent = new Intent(LoginActivity.this,NewLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        Log.d("LoginActivity", name);
                        String icon = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        setResult(RESULT, intent);
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });


                break;
        }
    }
}
