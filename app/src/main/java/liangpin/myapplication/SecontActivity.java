package liangpin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import liangpin.myapplication.loadingAnimation.ContentView;
import liangpin.myapplication.loadingAnimation.DNLoadingView;

/**
 * Created by Admin on 2017/1/12.
 */

public class SecontActivity extends Activity {
    private FrameLayout frameLayout;
    DNLoadingView dnLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frameLayout=new FrameLayout(this);
         dnLoadingView=new DNLoadingView(this);
        ContentView contentView=new ContentView(this);
        frameLayout.addView(contentView);
        frameLayout.addView(dnLoadingView);
        startLoad();
    }
    Handler handler=new Handler();
    public void startLoad(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dnLoadingView.SplashDisappear();
            }
        },5000);
    }
}
