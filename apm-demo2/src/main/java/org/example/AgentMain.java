package org.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * @author 王鹏
 */
public class AgentMain {

    public static void premain(String agentArgs,Instrumentation inst) {

        AgentBuilder agentBuilder = new AgentBuilder.Default();

        List<InterceptorPoint> pointList = PluginGroup.POINT_LIST;

        for (InterceptorPoint point : pointList) {

            AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
                builder = builder.visit(Advice.to(point.adviceClass()).on(point.buildMethodMatcher()));
                return builder;
            };

            agentBuilder = agentBuilder
                    .type(point.buildTypeMatcher())
                    .transform(transformer);
        }

        //监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
                System.out.println("onTransformation：" + typeDescription);
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

        };

        agentBuilder.with(listener).installOn(inst);




    }
}
