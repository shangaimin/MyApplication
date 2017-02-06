package liangpin.myapplication.insertAOP;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import liangpin.myapplication.R;

/**
 * Created by Admin on 2017/1/9.
 */
//插件化开发 非安装APk形式加载其他Apk种的文件
public class InsertActivity extends Activity implements View.OnClickListener {
    ImageView iv;
    Drawable drawable1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        iv= (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        final String fileName="com.yidd365.clerk_1.11.410_15910.apk";
        String filePath=this.getCacheDir()+ File.separator+fileName;
        String packgName="com.yidd365.clerk";
        final File file=new File(filePath);
        if(file.exists()){
            Drawable drawable=v.getBackground();
            try {
                AssetManager assetManager= PluginResource.getAssetManager(this.getResources(),file);
                Resources resources=PluginResource.getPluginResource(this.getResources(),assetManager);
                DexClassLoader classLoader=new DexClassLoader(file.getAbsolutePath(),this.getDir(fileName, Context.MODE_PRIVATE).getAbsolutePath(),
                        null,this.getClassLoader());
                Class<?> class1 = classLoader.loadClass(packgName+".R$drawable");
                Field[] fields=class1.getDeclaredFields();
                for(Field field:fields){
                    if(field.getName().equals("ic_home_selected")){
                        int id=field.getInt(R.drawable.class);
                         drawable1=resources.getDrawable(id);
                        iv.setBackground(drawable1);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is= getAssets().open(fileName);
                        OutputStream os= new FileOutputStream(file);
                        int len=0;
                        byte[] bytes=new byte[1024];
                        while((len=is.read(bytes))!=-1){
                            os.write(bytes,0,len);
                        }
                        os.close();
                        is.close();
                        Log.e("tag","下载完成");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
