package Cryptor;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class Decryptor {
    public static String decrypt(BufferedImage finalImg, long seed){
        Random generator = new Random(seed);
        HashMap<Integer,Integer> pixels = new HashMap<>();
        int height = finalImg.getHeight();
        int width = finalImg.getWidth();
        String message = "";

        while(true){
            int h = generator.nextInt(0, height);
            int w = generator.nextInt(0, width);
            if(!(pixels.containsKey(h) && pixels.get(h) == w)) {
                pixels.put(h, w);
            }
            int rgb = finalImg.getRGB(w, h);
            rgb &= 0xFFFFFF;
            char thisChar = (char) ( ((rgb >> 16) & 0x7) << 5 | ((rgb >> 8) & 0x3) << 3 | (rgb & 0x7) );
            if(thisChar > 0){
                message += thisChar;
            } else {
                return message;
            }
        }
    }
}
