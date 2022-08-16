package Key;

import java.util.Random;

public class Seed {
    public static long generateSeed(){
        Random generator = new Random();
        return generator.nextLong(0, 0xfffffffffffffffl);
    }
    public static String seedToString(long seed){
        return "0x" + Long.toHexString(seed).toUpperCase();
    }
    public static long seedToLong(String seed){
        seed = seed.toLowerCase();
        return Long.decode(seed);
    }
}
