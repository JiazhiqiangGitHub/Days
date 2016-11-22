package lanou.days;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import lanou.days.base.BaseActivity;
import lanou.days.birth.BirthFragment;
import lanou.days.note.NoteFragment;
import lanou.days.setting.SettingFragment;
import lanou.days.write.WriteFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton btnWrite,btnNote,btnBirth,btnSetting;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        btnWrite = bindView(R.id.btn_main_write);
        btnNote = bindView(R.id.btn_main_note);
        btnBirth = bindView(R.id.btn_main_birth);
        btnSetting = bindView(R.id.btn_main_setting);
    }

    @Override
    protected void initData() {
        btnWrite.setChecked(true);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.lb_main, new WriteFragment());
        transaction.commit();

        btnWrite.setOnClickListener(this);
        btnNote.setOnClickListener(this);
        btnBirth.setOnClickListener(this);
        btnSetting.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
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
            case R.id.btn_main_setting:
                transaction.replace(R.id.lb_main, new SettingFragment());
                break;
        }
        transaction.commit();

    }
}
