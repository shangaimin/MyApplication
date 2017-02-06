package liangpin.myapplication.inject.injectutils;

import android.app.Activity;
import android.icu.text.DateFormat;
import android.view.View;

import java.lang.reflect.Field;

import liangpin.myapplication.R;
import liangpin.myapplication.inject.activity.baseActivity;
import liangpin.myapplication.inject.injectview.ContentView;
import liangpin.myapplication.inject.injectview.Viewinject;

/**
 * Created by Admin on 2017/1/1.
 */
public class InjectUtils {
    public static void inject(Activity baseActivity) {
        injectLayout(baseActivity);
        injectViews(baseActivity);
        injectOnclick(baseActivity);
    }

    private static void injectOnclick(Activity baseActivity) {
        
    }

    private static void injectViews(Activity baseActivity) {
        Class<? extends Activity> clazz=baseActivity.getClass();
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields){
            Viewinject viewinject=field.getAnnotation(Viewinject.class);
            if(viewinject==null){
                continue;
            }
            int id=viewinject.value();
            View view=baseActivity.findViewById(id);
            field.setAccessible(true);
            try {
                field.set(baseActivity,view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    //注入布局
    private static void injectLayout(Activity baseActivity) {
        Class<? extends Activity> clazz=baseActivity.getClass();
        ContentView conentview=clazz.getAnnotation(ContentView.class);
        int layout=conentview.value();
        baseActivity.setContentView(layout);
    }
}
