import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
         /*
        BufferedImage initialImg = null;
        BufferedImage finalImg = null;
        try {
            initialImg = ImageIO.read(new File("src/initial.jpeg"));
        } catch (IOException e) {}
        encryptor(initialImg, 'Z');
        try {
            finalImg = ImageIO.read(new File("src/final.jpeg"));
        } catch (IOException e) {}
        decryptor(finalImg);
         */
        int rgb = 0b01111100_00101011_01100100;
        int red = ( rgb >>  16 ) & 255 & ~( (1 << 0) | (1 << 1) | (1 << 2) ); // 0b01111000 відділяємо байт червоного кольору та вимикаємо перші три біти для запису
        int green = ( rgb >>  8 ) & 255 & ~( (1 << 0) | (1 << 1) ); // 0b00101000 відділяємо байт зеленого кольору та вимикаємо перші два біти для запису
        int blue =  ( rgb >> 0 ) & 255 & ~( (1 << 0) | (1 << 1) | (1 << 2) ); // 0b01100000 відділяємо байт синього кольору та вимикаємо перші три біти для запису

        int ch ='z'; // 0b01100001
        int one = (ch >> 5); // 0b011
        int two = (ch >> 3) & 0x3; // 0b00
        int three = (ch>>0) & 0x7; // 0b001

        red |= one; // 0b01111011
        green |= two; // 0b00101000
        blue |= three; // 0b01100001

        int newColor = (red << 16 | green << 8 | blue); // 0b01111011_00101000_01100001

        red = ( newColor >>  16 ) & 0x7; // 0b01111000 відділяємо байт червоного кольору та вимикаємо перші три біти для запису
        green = ( newColor >>  8 ) & 0x3; // 0b00101000 відділяємо байт зеленого кольору та вимикаємо перші два біти для запису
        blue =  ( newColor >> 0 ) & 0x7; // 0b01100000 відділяємо байт синього кольору та вимикаємо перші три біти для запису

        int newChar = (red << 5 | green << 3 | blue);
        char chh = (char) newChar;
        System.out.println(String.valueOf(chh));

    }
    public static void encryptor(BufferedImage initialImg, char thisChar){
        int height = initialImg.getHeight();
        int width = initialImg.getWidth();
        int rgb;

        for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                /*
                rgb = img.getRGB(w, h);
                red = (rgb >> 16 ) & 0xAA;
                green = (rgb >> 8 ) & 0xAA;
                blue = (rgb) & 0xAA;
                rgb = (red << 16 | green << 8 | blue);
                img.setRGB(w, h, rgb);
                */
                rgb = initialImg.getRGB(w, h);
                int newColor = rgb;   // 11111000 00000000 00000000
                newColor |= (thisChar & 0xE0) << 11;     // 00000111 00000000 00000000
                newColor |= (rgb & (0x3F << 10));  // 00000000 11111100 00000000
                newColor |= (thisChar & 0x18) << 5;      // 00000000 00000011 00000000
                newColor |= (rgb & (0x1F << 3));   // 00000000 00000000 11111000
                newColor |= (thisChar & 0x7);            // 00000000 00000000 00000111

                initialImg.setRGB(w, h, newColor);
            }
        }
        try {
            ImageIO.write(initialImg, "jpeg", new File("src/final.jpeg"));
        } catch (IOException e) {}
    }
    public static void decryptor(BufferedImage finalImg){
        int height = finalImg.getHeight();
        int width = finalImg.getWidth();
        int rgb;

        for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                rgb = finalImg.getRGB(w, h);
                int thisChar = 0;
                thisChar |= (rgb & 0x70000) >> 11;  // 00000111 00000000 00000000 -> 00000000 00000000 11100000
                thisChar |= (rgb & 0x300) >> 5;     // 00000000 00000011 00000000 -> 00000000 00000000 00011000
                thisChar |= (rgb & 0x7);            // 00000000 00000000 00000111
                System.out.println((char) thisChar);
            }
        }
    }
}
