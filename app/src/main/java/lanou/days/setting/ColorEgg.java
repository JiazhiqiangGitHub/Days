package lanou.days.setting;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by 张家鑫 on 16/12/5.
 */
public class ColorEgg extends BaseActivity implements View.OnClickListener, Animation.AnimationListener {
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private static int angry = 0;
    private static int text = 0;
    private String[] name = {"一直","沉迷","拥有神秘","善良","持盾","耍剑","专业","业余","极其危险且","拥有着"};
    private String[] scName = {"开车技巧","吃翔","低级趣味","而伟大","勇猛","建设社会主义新中国","依旧单身","乐于碰瓷","男女通吃","卖萌"};
    private String[] thing = {"普通人","黑恶势力","程序员","Tiger Bee","老司机","少先队员","非洲酋长","小学生","键盘侠","菜鸡"};


    @Override
    protected int getLayout() {
        return R.layout.activity_coloregg;
    }

    @Override
    protected void initViews() {
        mImageView = bindView(R.id.iv_coloregg);
        mTextView = bindView(R.id.tv_coloregg);
        mLinearLayout = bindView(R.id.ll_coloregg);
        mImageView.setVisibility(View.VISIBLE);
        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
        mTextView.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        mImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_coloregg:
                switch (angry){
                    case 0:
                        mTextView.setText("不要再点了,给我点下面的文字");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        break;
                    case 1:
                        mTextView.setText("真是愚蠢的人类,点这句话啦!");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        break;
                    case 2:
                        mTextView.setText("最后通牒,不要再点我了,don't touch me!");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        break;
                    case 3:
                        finish();
                        angry = -1;
                        text = 0;
                        break;
                }
                angry++;
                break;
            case R.id.tv_coloregg:
                switch (text){
                    case 0:
                        mTextView.setText("不过你既然能找到这里,说明你是有缘之人");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        break;
                    case 1:
                        mTextView.setText("那么,想不想抽卡呢?证明一下自己的血统");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        break;
                    case 2:
                        mTextView.setText("那,就开始吧");
                        mTextView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.popup_enter));
                        Animation anim = AnimationUtils.loadAnimation(this, R.anim.coloregg_anim);
                        anim.setAnimationListener(this);
                        mLinearLayout.setAnimation(anim);
                        break;
                }
                text++;
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        angry = 0;
        mLinearLayout.setBackground(null);
            Random random=new Random();
            int mVal = random.nextInt(10);
            int mSeVal = random.nextInt(10);
            int mLasVal = random.nextInt(10);
            mTextView.setTextColor(Color.BLACK);
            mTextView.setText(name[mVal] + scName[mSeVal] + "的" + thing[mLasVal]);
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setClickable(false);
            mImageView.setImageResource(R.mipmap.deepdark);

    }
    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onDestroy() {
        text = 0;
        angry = 0;
        super.onDestroy();
    }
}
