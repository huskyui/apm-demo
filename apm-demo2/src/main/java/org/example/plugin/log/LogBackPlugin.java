package org.example.plugin.log;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.example.InterceptorPoint;
import org.example.trace.TraceContext;
import org.slf4j.MDC;

/**
 * @author 王鹏
 */
public class LogBackPlugin implements InterceptorPoint {


    @Override
    public ElementMatcher<TypeDescription> buildTypeMatcher() {
        return ElementMatchers.named("ch.qos.logback.classic.Logger");
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodMatcher() {
        return ElementMatchers.named("buildLoggingEventAndAppend");
    }

    @Override
    public Class<?> adviceClass() {
        return LogBackPluginAdvice.class;
    }

    public static class LogBackPluginAdvice{


        @Advice.OnMethodEnter(suppress = Throwable.class)
        public static void onEnter(){
            String traceId = TraceContext.getTraceId();
            if (traceId == null || traceId.isEmpty()){
                return;
            }
            MDC.put("apmTrace","traceId:"+traceId);
        }

        @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
        public static void stopSpan(){
            MDC.remove("apmTrace");
        }
    }

}
