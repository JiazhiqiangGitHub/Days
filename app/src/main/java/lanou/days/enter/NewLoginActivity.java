package lanou.days.enter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/23.
 */
public class NewLoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etNumber,etPassword;
    private ImageView btnBack;
    private Button btnOk;
    private String tel,telBlooean;
    private String world;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_login;
    }

    @Override
    protected void initViews() {
        etNumber = bindView(R.id.et_new_login_number);
        etPassword = bindView(R.id.et_new_login_password);
        btnBack = bindView(R.id.btn_new_login_back);
        btnOk = bindView(R.id.btn_enter_new_login);
    }

    @Override
    protected void initData() {
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        editTextChangeColor();
    }

    private void editTextChangeColor() {
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                telBlooean = etNumber.getText().toString().trim();
                if (telBlooean.length() == 11){
                    btnOk.setEnabled(true);
                    btnOk.setBackgroundColor(Color.BLUE);
                }else{
                    btnOk.setEnabled(false);
                    btnOk.setBackgroundColor(Color.GRAY);
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
            case R.id.btn_new_login_back:
                finish();
                break;
            case R.id.btn_enter_new_login:
                Login();
                break;
        }
    }

    private void Login() {
        tel = etNumber.getText().toString();
        world = etPassword.getText().toString();
        Log.d("aaaaaNewLoginActivity", tel);
        Log.d("aaaaaNewLoginActivity", world);
        if ((!tel.isEmpty())&&(!world.isEmpty())){
            Log.d("aaaaaNewLoginActivity", "Fdsfdsfdsf");
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(tel);
            bmobUser.setPassword(world);
            bmobUser.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null) {
                        Toast.makeText(NewLoginActivity.this, "注册成功喵", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(NewLoginActivity.this, "主人,用户名貌似被占用了喵", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "主人,请补全密码喵", Toast.LENGTH_SHORT).show();
        }
    }
}
