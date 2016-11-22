package lanou.days.birth;

import android.content.Intent;

import android.view.View;
import android.widget.Button;


import lanou.days.base.BaseFragment;

/**
 * Created by machuang on 16/11/22.
 */
public class BirthFragment extends BaseFragment implements View.OnClickListener {

    private Button birthModify;

    @Override
    protected void initDate() {
        setItemOnClick(this,birthModify);
    }

    @Override
    protected void initView() {
        birthModify = bindView(R.id.btn_birth_modify);
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
                startActivity(intent);
                break;
        }
    }
}
