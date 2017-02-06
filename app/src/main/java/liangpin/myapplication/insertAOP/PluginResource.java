package liangpin.myapplication.insertAOP;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Admin on 2017/1/9.
 */

public class PluginResource extends Resources{
    public PluginResource(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration cog){
        super(assetManager,displayMetrics,cog);
    }
    public static AssetManager getAssetManager(Resources res,File file) throws ClassNotFoundException {
       Class<?> c= Class.forName("android.content.res.AssetManager");
        Method[] methods=c.getDeclaredMethods();
        for(Method m:methods){
            if(m.getName().equals("addAssetPath")){
                try {
                    AssetManager asset=AssetManager.class.newInstance();
                    m.invoke(asset,file.getAbsolutePath());
                    return asset;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }
    public static PluginResource getPluginResource(Resources r,AssetManager asset){
        return new PluginResource(asset,r.getDisplayMetrics(),r.getConfiguration());
    }
}
