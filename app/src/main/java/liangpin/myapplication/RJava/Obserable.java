package liangpin.myapplication.RJava;

/**
 * Created by Admin on 2016/12/24.
 */
/*
    黑屋子 平安夜
    T 代表看电影
 */
public class Obserable<T> {
    private OnSubscribe<T> onSubscribe;
    public Obserable( OnSubscribe<T> onSubscribe){
        this.onSubscribe=onSubscribe;
    }
    public void subscribe(Subscribe<? super T> subscribe){
        onSubscribe.onCall(subscribe);
    }
    public static <T> Obserable<T> create(OnSubscribe<T> on){
            return new Obserable<T>(on);
    }

}
