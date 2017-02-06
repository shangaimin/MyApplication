package liangpin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import liangpin.myapplication.RJava.Obserable;
import liangpin.myapplication.RJava.OnSubscribe;
import liangpin.myapplication.aop.BehaivorTrace;
import liangpin.myapplication.aop.BehaviorAspect;
import rx.Observable;

/**
 * Created by Admin on 2016/12/21.
 */

public class ZoomActivity extends Activity{
    private QQZoneHeadZoomView qqZoneHeadZoomView;
    private View view;
    private ImageView headview;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        button= (Button) findViewById(R.id.test);
        qqZoneHeadZoomView= (QQZoneHeadZoomView) findViewById(R.id.lv);
        setData();
    }
    private void send(){
       
    }
    @BehaivorTrace("加载数据")
    private void setData(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                new String[]{"123","123","123","123","123"
                        ,"123","123","123"
                        ,"123","123","123"
                        ,"123","123"
                });
        view= LayoutInflater.from(this).inflate(R.layout.listview_header,null);
        headview= (ImageView) view.findViewById(R.id.headview);
        qqZoneHeadZoomView.setImageview(headview);
        qqZoneHeadZoomView.addHeaderView(view);
        qqZoneHeadZoomView.setAdapter(arrayAdapter);
    }
    private void initData(){
    }
}
