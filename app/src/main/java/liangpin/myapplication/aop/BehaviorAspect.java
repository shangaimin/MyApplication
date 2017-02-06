package liangpin.myapplication.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Admin on 2016/12/23.
 */
@Aspect
public class BehaviorAspect {
    @Pointcut("execution (@liangpin.myapplication.aop.BehaivorTrace * *(..))")
    public void methodAnnotatedBehaivorTrace(){}
    @Around("methodAnnotatedBehaivorTrace()")
    public Object wavejoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        long time =System.currentTimeMillis();
        Object set=joinPoint.proceed();
        long duration=System.currentTimeMillis()-time;
        MethodSignature ms= (MethodSignature) joinPoint.getSignature();
        BehaivorTrace anno= ms.getMethod().getAnnotation(BehaivorTrace.class);
        String func=anno.value();
        Log.e("json",String.format("%s 功能耗时 %d ms",func,duration));
        return set;
    }
}
