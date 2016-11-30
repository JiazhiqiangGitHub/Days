package lanou.days.setting;

import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import lanou.days.R;
import lanou.days.base.BaseFragment;

/**
 * Created by dllo on 16/11/22.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mImageView;
    long [] mHits = new long[3];
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mImageView = bindView(R.id.iv_setting_alipay);
        mImageView.setOnClickListener(this);


    }

    @Override
    protected int getLayout() {

      return R.layout.fragment_setting;

//        return R.layout.mine_fragment;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_alipay:
                /**
                 * System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);数组的复制，
                 * 第一个参数：要被拷贝的数组；
                 * 第二个参数：从哪个位置开始拷贝（这里就是从1开始）；
                 * 第三个参数：目标数组；
                 * 第四个参数：从目标数组的哪个位置开始存放（这里是从0开始）；
                 * 第五个参数：拷贝的长度。
                 * 通过这个方法，我们实现了对每一个点击事件的时间进行记录，可以判断任意连续3次点击是否视为3击事件。
                 */
                System.arraycopy(mHits,1,mHits,0,mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis(); // 开机后运行的时间
                /**
                 * 判断数组中下标为2的点击事件的时间与数组中下标为0的点击事件的时间差值是否小于500
                 */
                if (mHits[0] >= (mHits[mHits.length - 1] - 500)) {
                    // 点击了三次,进行跳转
                    Toast.makeText(getContext(), "点击了三次", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
