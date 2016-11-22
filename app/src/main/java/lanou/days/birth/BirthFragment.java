package lanou.days.birth;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import lanou.days.R;
import lanou.days.base.BaseFragment;

/**
 * Created by machuang on 16/11/22.
 */
public class BirthFragment extends BaseFragment implements View.OnClickListener {

    private Button birthModify;
    private TextView birthdayTv;
    public static int REQUEST = 1;

    @Override
    protected void initDate() {
        setItemOnClick(this,birthModify);
    }

    @Override
    protected void initView() {
        birthModify = bindView(R.id.btn_birth_modify);
        birthdayTv = bindView(R.id.tv_birthday);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_birth;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_birth_modify:
                Intent intent = new Intent(getContext(),ModifyBirthDayActivity.class);
                startActivityForResult(intent,REQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && ModifyBirthDayActivity.RESULT == resultCode) {
            Log.d("BirthFragment", data.getStringExtra("birthday"));
            birthdayTv.setText(data.getStringExtra("birthday"));
        }
    }
}
