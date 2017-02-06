package liangpin.myapplication.install;

import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Admin on 2017/1/17.
 */

public class apkinstaller {
    Context context;
    File file;
    public void installer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean sucess=installerbyRoot();
                if(!sucess){
                    //智能安装 开启服务
                    if(isAccessBilityOn()){
                        Uri uri=Uri.fromFile(file);
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri,"application/vm.android.package-archive");
                        context.startActivity(intent);
                    }else{
                        Intent intent=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        context.startActivity(intent);
                    }
                }

            }


        });
    }
    private boolean isAccessBilityOn() {
        int i;
        String service= context.getPackageName()+"/"+ApkService.class.getCanonicalName();
        try {
            i=Settings.Secure.getInt(context.getApplicationContext().getContentResolver(),Settings.Secure.ACCESSIBILITY_ENABLED);
        if(i==1){
            String settingValue=Settings.Secure.getString(context.getApplicationContext().getContentResolver(),Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            String []arr=settingValue.split(":");
        }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean installerbyRoot(){
        boolean result=false;
        Process process=null;
        OutputStream os=null;
        String common=null;
        BufferedReader br=null;
        StringBuffer sb=null;
        try {
            process=Runtime.getRuntime().exec("su");
            os=process.getOutputStream();
            os.write(("pm install -r").getBytes());
            os.flush();
            os.write("exit\n".getBytes());
            process.waitFor();
            br =new BufferedReader(new InputStreamReader(process.getErrorStream()));
            sb=new StringBuffer();
            String line;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            if(!sb.toString().contains("Failure")){
                result= true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }finally {
                try {
                    if(os!=null) {
                        os.close();
                    }
                    if(br!=null){
                        br.close();
                    }
                    if(process!=null){
                        process.destroy();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return result;
    }
}
