package liangpin.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;

import liangpin.myapplication.gen.DaoMaster;
import liangpin.myapplication.gen.DaoSession;

/**
 * Created by Admin on 2017/2/6.
 */

public class MyApplication extends Application {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase sb;
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        setGreenDao();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());;
    }

    private void setGreenDao() {
        devOpenHelper=new DaoMaster.DevOpenHelper(this,"local_min",null);
        sb=devOpenHelper.getWritableDatabase();
        daoMaster=new DaoMaster(sb);
        daoSession=daoMaster.newSession();
    }

    public static MyApplication getInstances(){
        return mInstance;
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public SQLiteDatabase getDb() {
        return sb;
    }

}
