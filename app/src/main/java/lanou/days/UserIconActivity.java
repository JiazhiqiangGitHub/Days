package lanou.days;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/12/8.
 */
public class UserIconActivity extends BaseActivity implements View.OnClickListener {
    private CircleImageView ivUser;
    private Button btnUser;
    private ImageView btnBack;
    @Override
    protected int getLayout() {
        return R.layout.activity_usericon;
    }

    @Override
    protected void initViews() {
        ivUser = bindView(R.id.iv_usericon);
        btnUser = bindView(R.id.btn_usericon);
        btnBack = bindView(R.id.btn_user_back);


    }

    @Override
    protected void initData() {
        btnUser.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_usericon:
                Intent intent = new Intent();
                //开启Picture画面Type设定为image
                intent.setType("image");
                //使用Intent.ACTION_GET_CONTENT这个Action
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //取得相片之后返回画面
                startActivityForResult(intent,10);
                break;
            case R.id.btn_user_back:
                finish();
                break;
        }

    }
    //// TODO: 16/12/8  

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK){
//            Uri uri = data.getData();
//
//            ContentResolver cr = this.getContentResolver();
//        }
//
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
