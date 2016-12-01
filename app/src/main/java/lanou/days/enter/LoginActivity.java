package lanou.days.enter;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import lanou.days.MainActivity;
import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/23.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etTelephone,etPassword;
    private ImageView btnBcak;
    private Button btnEnter,btnNew;
    private String tel;
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

    }

    @Override
    protected void initData() {
        btnBcak.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        //按钮变色
        EditTextChangeColor();

    }

    private void EditTextChangeColor() {
        etTelephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tel = etTelephone.getText().toString().trim();
                if (tel.length() == 11){
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
                String number = etTelephone.getText().toString();
                String world = etPassword.getText().toString();
                BmobUser myUser = new BmobUser();
                myUser.setUsername(number);
                myUser.setPassword(world);
                myUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
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
        }
    }
}
