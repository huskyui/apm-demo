package org.example.trace;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 王鹏
 */
public class IDGen {
    public static long newSpanId() {
        Random random = getRandom();
        return createSpanId(random);
    }

    private static Random getRandom() {
        return ThreadLocalRandom.current();
    }

    private static long createSpanId(Random seed) {
        long id;
        for(id = seed.nextLong(); id == -1L; id = seed.nextLong()) {
        }
        return id >> 12;
    }
}
