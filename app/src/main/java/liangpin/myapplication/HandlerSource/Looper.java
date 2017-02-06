package liangpin.myapplication.HandlerSource;

/**
 * Created by Admin on 2017/1/8.
 */

public class Looper {
    static ThreadLocal<Looper> sThreadLocal=new ThreadLocal<Looper>();
    MessageQueue mQueue;
    private Looper(){

    }
//    实例化Looper对象
    public static void prepare(){
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }
//    获取当前线程Looper对象
    public static  Looper myLooper(){
        return sThreadLocal.get();
    }
//    轮询消息队列
    public void loop(){
        Looper me=myLooper();
        if(me==null){
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        for(;;){
            Message msg=mQueue.next();
            if(msg==null) {
                continue;
            }
            msg.target.handleMessage(msg);
        }


    }
}
