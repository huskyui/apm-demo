package org.example.plugin.tomcat;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.example.InterceptorPoint;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author 王鹏
 */
public class TomcatPlugin implements InterceptorPoint {
    @Override
    public ElementMatcher<TypeDescription> buildTypeMatcher() {
        return ElementMatchers.named("org.apache.catalina.core.StandardHostValve");
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodMatcher() {
        return ElementMatchers.named("invoke");
    }

    @Override
    public Class<?> adviceClass() {
        return TomcatPluginAdvice.class;
    }

    public static class TomcatPluginAdvice{
        @RuntimeType
        public static Object intercept(
                @Origin Method method, @AllArguments Object[] args,
                @SuperCall Callable<?> callable)
                throws Exception {
            try {
                //执行原方法
                return callable.call();
            } finally {

            }
        }
    }
}
