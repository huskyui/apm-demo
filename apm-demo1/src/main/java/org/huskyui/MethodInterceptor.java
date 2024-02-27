package org.huskyui;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author 王鹏
 */

public class MethodInterceptor {

    @RuntimeType
    public static Object intercept(
            @Origin Method method, @AllArguments Object[] args,
            @SuperCall Callable<?> callable)
            throws Exception {
        long start = System.currentTimeMillis();
        if (method.getName().equals("say")){
            args[0]= "hello,world!";
        }
        try {
            //执行原方法
            Object call = callable.call();
            System.out.println("调用: " + call);
            return call;
        } finally {
            //打印调用时长
            System.out.println("调用类名：" + method.getDeclaringClass().getName() + "\n"
                    + "方法名：" + method.getName() + "\n"
                    +"参数："+ Arrays.toString(args) +"\n"
                    + "耗时：" + (System.currentTimeMillis() - start) + "ms" + "\n");
        }
    }

}
