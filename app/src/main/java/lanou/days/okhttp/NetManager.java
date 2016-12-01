package lanou.days.okhttp;

/**
 * Created by 贾志强 on 16/11/25.
 */

public abstract class NetManager {

    protected abstract <Bean> void get(String url,Class<Bean> clazz, ResponseCallBack<Bean> responseCallBack);
}
