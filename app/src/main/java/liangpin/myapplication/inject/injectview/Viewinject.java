package liangpin.myapplication.inject.injectview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Admin on 2017/1/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //用在属性上
public @interface Viewinject {
    int value();
}
