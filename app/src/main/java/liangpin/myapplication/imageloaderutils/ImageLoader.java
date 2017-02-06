package liangpin.myapplication.imageloaderutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2017/2/3.
 */

public class ImageLoader {
    //
    private static Context context;
    private static final int MAX_CAPACTY=20;
    private static  LinkedHashMap<String,SoftReference<Bitmap>> firstCacheMap=new LinkedHashMap<String,SoftReference<Bitmap>>(MAX_CAPACTY){
        @Override
        protected boolean removeEldestEntry(Entry<String, SoftReference<Bitmap>> eldest) {
            if(this.size()>MAX_CAPACTY){
                return true;
            }
            //磁盘缓存
            diskCache(eldest.getKey(),eldest.getValue());
            return false;
        }
    };
    public void LoadImage(String key, ImageView view){
        synchronized (view){
            Bitmap bitmap=getFromCache(key);
            if(bitmap!=null){
                view.setImageBitmap(bitmap);
            }else{
                //下载
            }
        }
    }

    private Bitmap getFromCache(String key) {
        synchronized (firstCacheMap){
            if(firstCacheMap.get(key)!=null){
                Bitmap bitmap=firstCacheMap.get(key).get();
                if(bitmap!=null){
                    firstCacheMap.put(key,new SoftReference<Bitmap>(bitmap));
                    return bitmap;
                }
            }
        }
        return null;
    }

    private static void diskCache(String key, SoftReference<Bitmap> value) {
        //消息摘要算法
        String filename=key;
        String path=context.getCacheDir().getAbsolutePath()+"/"+filename;
        FileOutputStream os=null;
        try {
            os=new FileOutputStream(new File(path));
            if(value.get()!=null){
                value.get().compress(Bitmap.CompressFormat.JPEG,100,os);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
