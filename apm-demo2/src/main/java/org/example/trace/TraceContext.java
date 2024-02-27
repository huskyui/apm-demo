package org.example.trace;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 王鹏
 */
public class TraceContext {
    private static final ThreadLocal<String> traceContext = new ThreadLocal<String>();

    public static void setTraceId(String traceId){
        traceContext.set(traceId);
    }

    public static String getTraceId(){
        return traceContext.get();
    }

    public static void remove(){
        traceContext.remove();
    }



}
