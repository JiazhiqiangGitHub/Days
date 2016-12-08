package lanou.days;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;
import lanou.days.base.BaseActivity;

/**
 * Created by dllo on 16/12/8.
 */
public class UserIconActivity extends BaseActivity implements View.OnClickListener {
    private CircleImageView ivUser;
    private Button btnUser,btnOk;
    private ImageView btnBack;
    private Bitmap bitmap;
    private String b;

    @Override
    protected int getLayout() {
        return R.layout.activity_usericon;
    }

    @Override
    protected void initViews() {
        ivUser = bindView(R.id.iv_usericon);
        btnUser = bindView(R.id.btn_usericon);
        btnBack = bindView(R.id.btn_user_back);
        btnOk = bindView(R.id.btn_usericon_ok);

    }

    @Override
    protected void initData() {
        btnUser.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        String a = bmobUser.getObjectId();
        if (bmobUser != null) {
            BmobQuery<MyUser> bmobQuery = new BmobQuery<MyUser>();
            bmobQuery.addWhereEqualTo("objectId", a);
            bmobQuery.findObjects(new FindListener<MyUser>() {
                @Override
                public void done(List<MyUser> list, BmobException e) {
                    if (e == null) {
                        b = list.get(0).getIcon();
                        Log.d("MainActivity", b);
                        Picasso.with(getBaseContext()).load(b).into(ivUser);

                    }
                }
            });

        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_usericon:
                Intent intent = new Intent("android.intent.action.PICK");
                //开启Picture画面Type设定为image
                intent.setType("image/*");
                //取得相片之后返回画面
                startActivityForResult(intent,10);
                break;
            case R.id.btn_user_back:
                finish();
                break;
            case R.id.btn_usericon_ok:
                upLoadIcon();
                finish();
                break;
        }

    }

    private void upLoadIcon() {
        //先检测是否登录
        final MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        if (myUser == null){
            Toast.makeText(this, "你还木有登录", Toast.LENGTH_SHORT).show();
        }else{
            if(bitmap == null){
                return;
            }
            //getCacheDir是Android提供的缓存路径
            //位置 是包名/cache 该方法是Context的方法,可以使用Application的Context
            File cacheDir = getCacheDir();
            if (!cacheDir.exists()){
                //如果路径不存在
                //就创建这个文件夹
                cacheDir.mkdir();
            }
            //文件名加上时间 防止重复
            long time = System.currentTimeMillis();
            File iconFile = new File(cacheDir,myUser.getUsername()+time+".png");
            if (!iconFile.exists()){
                //如果文件不存在
                try {
                    //创建文件夹
                    iconFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //文件输出流
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(iconFile);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                try {
                    fileOutputStream.close();
                    //存图片
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //上传File
            final BmobFile bmobFile = new BmobFile(iconFile);
            //上传
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null){
                        Toast.makeText(UserIconActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        String fileUrl = bmobFile.getFileUrl();
                        //把图片的url存到用户列表里
                        myUser.setIcon(fileUrl);
                        myUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                               if (e==null){
                                   Toast.makeText(UserIconActivity.this, "存储url成功", Toast.LENGTH_SHORT).show();


                               }else{
                                   Toast.makeText(UserIconActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
                               }
                            }
                        });

                    }else{
                        Toast.makeText(UserIconActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null){
            return;
        }
        bitmap = null;
        if (requestCode == 10){
            //获取从相册界面返回的缩略图
            bitmap = data.getParcelableExtra("data");
            if (bitmap == null){
                try {
                    //获得URI得到的输入流
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    //通过输入流得到的bitmap对象
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        ivUser.setImageBitmap(bitmap);
    }
}
