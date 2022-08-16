package Cryptor;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class Encryptor {
    public static void encrypt(BufferedImage initialImg, String message, long seed){
        Random generator = new Random(seed);
        HashMap<Integer,Integer> pixels = new HashMap<>();
        int height = initialImg.getHeight();
        int width = initialImg.getWidth();
        message += '\0';
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int h = generator.nextInt(0, height);
            int w = generator.nextInt(0, width);
            if(!(pixels.containsKey(h) && pixels.get(h) == w)) {
                pixels.put(h, w);
                int rgb = initialImg.getRGB(w, h);
                rgb = (rgb & 0xFFFFFF) & 0xF8FCF8;
                rgb |= (((chars[i] >> 5) << 16) | ((chars[i] >> 3 & 0x3) << 8) | (chars[i] & 0x7)); // 0b01111011_00101000_01100001

                initialImg.setRGB(w, h, rgb);
            }
        }
    }
}

