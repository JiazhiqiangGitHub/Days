package lanou.days.birth.tool;

import android.app.Application;
import android.content.Context;



/**
 * Created by dllo on 16/10/25.
 */
public class MyApp extends Application{
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

    }
    public static Context getContext(){return sContext;}
}
