package lanou.days.birth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/11/23.
 */
public class FriendsActivity extends BaseSwipeActivity implements View.OnClickListener {

    private ImageView addIv;
    private TextView back;
    private ImageView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_friends);
        addIv = (ImageView) findViewById(R.id.iv_add);
        back = (TextView) findViewById(R.id.tv_back);
        more = (ImageView) findViewById(R.id.iv_more);
        addIv.setOnClickListener(this);
        back.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_add:
                startActivity(new Intent(this,AddFriendsActivity.class));
                break;
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.iv_more:

                break;
        }
    }

}
