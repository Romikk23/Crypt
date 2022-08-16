// created by @Romikk23
// Encryptor text in png photo and decrypt it with key
package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static Cryptor.Decryptor.decrypt;
import static Cryptor.Encryptor.encrypt;
import static Key.Seed.*;

public class Main {
    public static BufferedImage initialImg;
    public static BufferedImage finalImg;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select operation ( 1 - encrypt, 2 - decrypt, 0 - exit ): ");
        int choise = Integer.valueOf(scanner.nextLine());
        switch (choise){
            case 1:
                try {
                    initialImg = ImageIO.read(new File("src/images/initial.png"));
                } catch (IOException e) {}
                System.out.print("Print message to encrypt: ");
                String message = scanner.nextLine();
                long seed = generateSeed();
                System.out.println("New seed generated "+"\u001B[32m"+"successfully!"+"\u001B[0m"+"\nKey: "+"\u001B[31m" + seedToString(seed)+"\u001B[0m");
                encrypt(initialImg, message, seed);
                try {
                    ImageIO.write(initialImg, "png", new File("src/images/final.png"));
                } catch (IOException e) {}
                System.out.println("Message has encrypted "+"\u001B[32m"+"successfully!"+"\u001B[0m");
                main(args);
                break;
            case 2:
                System.out.print("Print key to decrypt: ");
                long key = seedToLong(scanner.nextLine());
                try {
                    finalImg = ImageIO.read(new File("src/images/final.png"));
                } catch (IOException e) {}
                String decryptedMessage = decrypt(finalImg, key);
                System.out.println("\nDecrypted message: "+ "\u001B[33m" + decryptedMessage + "\u001B[0m");
                main(args);
                break;
            case 0:
                return;
            default:
                main(args);
                break;
        }
    }
}
