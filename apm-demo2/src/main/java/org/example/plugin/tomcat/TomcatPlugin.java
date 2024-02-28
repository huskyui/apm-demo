package org.example.plugin.tomcat;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.example.InterceptorPoint;
import org.example.constant.HttpHeaderConstant;
import org.example.trace.IDGen;
import org.example.trace.TraceContext;

/**
 * @author 王鹏
 */
public class TomcatPlugin implements InterceptorPoint {
    @Override
    public ElementMatcher<TypeDescription> buildTypeMatcher() {
        return ElementMatchers.named("org.apache.catalina.connector.CoyoteAdapter");
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodMatcher() {
        return ElementMatchers.named("service");
    }

    @Override
    public Class<?> adviceClass() {
        return TomcatPluginAdvice.class;
    }

    public static class TomcatPluginAdvice{
        @Advice.OnMethodEnter(suppress = Throwable.class)
        public static void onEnter(
                @Advice.Argument(0) Request request,
                @Advice.Argument(1) Response response){
            System.out.println("tomcatAdvice enter");
            String traceId = request.getHeader(HttpHeaderConstant.APM_TRACE_ID);
            boolean existsTraceId = traceId != null && !traceId.isEmpty();
            if (existsTraceId){
                TraceContext.setTraceId(traceId);
            }else{
                TraceContext.setTraceId(String.valueOf(IDGen.newSpanId()));
            }
            System.out.println("traceId:"+TraceContext.getTraceId());
        }

        @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
        public static void stopSpan(){
            TraceContext.remove();
        }

    }
}
