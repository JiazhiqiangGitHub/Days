package lanou.days.write;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * 应用到的Bean,存放笔记和相关数据
 * Created by dllo on 16/11/24.
 */

public class WriteBean extends BmobObject{
    private String title;
    private String content;
    /**
     * 关联用
     */
    private BmobUser author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobUser getAuthor() {
        return author;
    }
    public void setAuthor(BmobUser author) {
        this.author = author;
    }


}
