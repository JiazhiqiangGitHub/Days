package lanou.days;

import cn.bmob.v3.BmobUser;

/**
 * Created by 贾志强 on 16/12/8.
 */

public class MyUser extends BmobUser {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
