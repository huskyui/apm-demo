package org.example;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.say("hhhh!");
    }

    private String say(String msg) {
        return "tom:" + msg;
    }


    @Override
    public String toString() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "agent-test-toString";
    }


//    private static Random getRandom() {
//        return ThreadLocalRandom.current();
//    }
//
//    private static long createSpanId(Random seed) {
//        long id;
//        for(id = seed.nextLong(); id == -1L; id = seed.nextLong()) {
//        }
//
//        return id >> 12;
//    }

}
