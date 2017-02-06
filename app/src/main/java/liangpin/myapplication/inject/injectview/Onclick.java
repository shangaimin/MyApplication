package liangpin.myapplication.inject.injectview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Admin on 2017/1/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)//用在方法上
public @interface Onclick {
    int[] value();
}
