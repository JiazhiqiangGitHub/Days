package lanou.days.birth.tool;

import android.os.Handler;
import android.os.Looper;


import com.litesuits.orm.LiteOrm;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 *
 * 单例实现 线程池, LiteOrm 数据库, Handler的初始化
 */
public class SingletonUtils {

    private static SingletonUtils singletonUtils;
    private ThreadPoolExecutor threadPoolExecutor;
    private LiteOrm liteOrm;
    private Handler handler;

    private SingletonUtils(String DBName) {
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maxPoolSize = corePoolSize * 2 + 1;
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60l,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        liteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), DBName);
        // Handler 只能在 主线程里 new
        handler = new Handler(Looper.getMainLooper());
    }

    public static SingletonUtils getInstance(String DBName) {

        if (singletonUtils == null) {
            synchronized (SingletonUtils.class) {
                if (singletonUtils == null) {
                    singletonUtils = new SingletonUtils(DBName);
                }
            }
        }

        return singletonUtils;
    }

    /* 获取 线程池的 方法 */
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    /* 获取 LiteOrm 数据库 的方法*/
    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    /* 获取 Handler 的方法 */
    public Handler getHandler() {
        return handler;
    }

}
