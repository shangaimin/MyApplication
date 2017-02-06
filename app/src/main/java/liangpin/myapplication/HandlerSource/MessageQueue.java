package liangpin.myapplication.HandlerSource;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2017/1/8.
 */

public class MessageQueue {
    Message[] items;
//    入队和出对的索引位置
    int putindex;
    int takeindex;
    int count;
    Lock lock;
//    条件变量
    private Condition notEmpty;
    private Condition notFull;

    public MessageQueue(){
        this.items=new Message[50];
        this.lock=new ReentrantLock();
        this.notEmpty=lock.newCondition();
        this.notFull=lock.newCondition();
    }
//    消息入队 生产者
    public void enqueueMessage(Message msg){
        try {
            lock.lock();
            while (count == items.length) {
//            阻塞
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.items[putindex] = msg;
            putindex = (++putindex == items.length) ? 0 : putindex;
            count++;
            //            生产了产品。通知消费者线程消费

            notEmpty.notifyAll();
        }finally {
            lock.unlock();
        }
    }
    //消息出队列 消费者
    public Message next(){
        try {
            while (count == 0) {
//            阻塞
                try {
                    notEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message msg = null;
            msg = items[takeindex];//  取出
            items[takeindex] = null;//元素置空
            takeindex = (++takeindex == items.length) ? 0 : takeindex;
            count--;
            //            消费了产品。通知生产者生产
            notFull.notifyAll();
            return msg;
        }finally {
            lock.unlock();
        }
    }
}
