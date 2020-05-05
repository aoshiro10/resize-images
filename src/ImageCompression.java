import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageCompression {
    public static void main(String[] args) throws IOException {

        File inPath = new File("test.jpg");
        File outPath = new File("output.bmp");



        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        boolean result = ImageIO.write(jpgImage, "BMP", bmpBytes);
        byte [] data = bmpBytes.toByteArray();

        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

        System.out.println(result);

//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        ImageIO.write(bufferimage, "jpg", output );
//        byte [] data = output.toByteArray();
//
//
//
//        data[1000] = data[1];
//        data[1004] = data[1];
//
//        String base64 = new String(data);
//        ByteArrayInputStream bis = new ByteArrayInputStream(data);
//        BufferedImage bImage2 = ImageIO.read(bis);
//        ImageIO.write(bImage2, "jpg", new File("output.jpg") );
//        System.out.println("image created");
//        System.out.println(base64.length());
////        System.out.println(base64);
    }
}
