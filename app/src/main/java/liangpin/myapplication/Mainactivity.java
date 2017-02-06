package liangpin.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Admin on 2016/12/31.
 */

public class Mainactivity extends Activity {
    private WebView web;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percent);
        web= (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.addJavascriptInterface(new JavaScriptObject(this),"Myobj");
        web.loadUrl("file:///android_asset/banner.html");
    }
    public class JavaScriptObject {
        Context mContxt;
        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }
        @JavascriptInterface
        public void fun1FromAndroid(String name) {
            Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
        }
        @JavascriptInterface
        public void fun2(String name) {
            Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
        }
    }
}
