package liangpin.myapplication.install;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.HashMap;

/**
 * Created by Admin on 2017/1/17.
 */

public class ApkService extends AccessibilityService {
    HashMap<Integer,Boolean> hashMap=new HashMap<>();
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo nodeInfo=event.getSource();
        if(nodeInfo!=null){
            //模拟点击 安装 完成 确定
            boolean hander=interNoderInfo(nodeInfo);
            if(hander){
                hashMap.put(event.getWindowId(),true);
            }
        }
    }

    /**
     * 模拟点击
     * @param nodeInfo
     * @return
     */
    private boolean interNoderInfo(AccessibilityNodeInfo nodeInfo) {
        int childCount=nodeInfo.getChildCount();
        if(nodeInfo.getClassName().equals("android.widget.Buttom")){
            String nodeContent=nodeInfo.getText().toString();
            if(nodeContent.equals("确定")||nodeContent.equals("完成")||nodeContent.equals("安装")){
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }else if(nodeInfo.getClassName().equals("android.widget.ScrollView")){
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }
        for(int i=0;i<childCount;i++){
            AccessibilityNodeInfo noteinfos=nodeInfo.getChild(i);
            if(noteinfos!=null) {
                if (interNoderInfo(noteinfos)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }
}
