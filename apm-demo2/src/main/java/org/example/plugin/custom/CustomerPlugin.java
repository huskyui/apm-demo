package org.example.plugin.custom;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.example.InterceptorPoint;

/**
 * @author 王鹏
 */
public class CustomerPlugin implements InterceptorPoint {
    @Override
    public ElementMatcher<TypeDescription> buildTypeMatcher() {
        return ElementMatchers.named("com.example.webtest.Api");
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodMatcher() {
        return ElementMatchers.isMethod()
                .and((ElementMatchers.named("hello")));
    }

    @Override
    public Class<?> adviceClass() {
        return CustomerAdviceClass.class;
    }


    public static class CustomerAdviceClass {
        @Advice.OnMethodExit()
        public static void exit() {
            JvmStack.printMemoryInfo();
            JvmStack.printGCInfo();
        }
    }

}
