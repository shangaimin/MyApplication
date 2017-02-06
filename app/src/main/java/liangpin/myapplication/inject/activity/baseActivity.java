package liangpin.myapplication.inject.activity;

import android.app.Activity;
import android.os.Bundle;

import liangpin.myapplication.inject.injectutils.InjectUtils;
import liangpin.myapplication.inject.injectview.Viewinject;

/**
 * Created by Admin on 2017/1/1.
 */

public class baseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }
}
