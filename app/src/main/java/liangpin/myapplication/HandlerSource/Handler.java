package liangpin.myapplication.HandlerSource;

/**
 * Created by Admin on 2017/1/8.
 */

public class Handler {
    Looper mLooper;
    MessageQueue mQueue;
    public Handler(){
        mLooper=Looper.myLooper();
//        this.mQueue=mLooper.mQueue;
    }
    public void handleMessage(Message msg){

    }
    public void sendMessage(Message msg){
        msg.target=this;
        mQueue.enqueueMessage(msg);
    }

}
