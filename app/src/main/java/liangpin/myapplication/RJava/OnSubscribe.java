package liangpin.myapplication.RJava;



/**
 * Created by Admin on 2016/12/24.
 * Subscribe<? super T>
 *     super用于参数类型的限定，不能用于返回参数的限定
 *     extends用于返回参数的限定，不能用于参数类型限定
 */

public interface OnSubscribe<T> extends Action1<Subscribe<? super T>> {


}
