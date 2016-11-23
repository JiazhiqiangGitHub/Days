package lanou.days.opening;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;

import lanou.days.MainActivity;
import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * 欢迎页
 * Created by dllo on 16/11/23 喵.
 */

public class OpeningActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll;
    private CountDownTimer timer;
    @Override
    protected int getLayout() {
        return R.layout.activity_opening;
    }

    @Override
    protected void initViews() {
        ll = bindView(R.id.ll_opening);
        ll.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        timer = new CountDownTimer(3500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }
    @Override
    public void onClick(View view) {
        timer.onFinish();
        timer.cancel();
    }
}
