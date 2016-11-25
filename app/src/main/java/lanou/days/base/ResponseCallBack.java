package lanou.days.base;

/**
 * Created by 贾志强 on 16/11/25.
 */

public interface ResponseCallBack<Bean> {
    //请求成功 直接返回 数据类
    void onResponse(Bean bean);

    //请求失败,返回异常信息
    void onError(Exception exception);
}
