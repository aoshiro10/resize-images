import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;

public class ImageResize {
    public static void main(String[] args) throws IOException {

        File inPath = new File("test.jpg");

        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        boolean result = ImageIO.write(jpgImage, "BMP", bmpBytes);
        byte [] data = bmpBytes.toByteArray();

        int dataOffset = data[10];
        byte[] pixelsPerRowBytes = Arrays.copyOfRange(data, 18, 22);

        System.out.println(Arrays.toString(pixelsPerRowBytes));
        for (int i = 0; i < pixelsPerRowBytes.length / 2; i++) {
            int iVerse = pixelsPerRowBytes.length - i - 1;
            byte temp = pixelsPerRowBytes[iVerse];
            pixelsPerRowBytes[iVerse] = pixelsPerRowBytes[i];
            pixelsPerRowBytes[i] = temp;
        }
        System.out.println(Arrays.toString(pixelsPerRowBytes));
        int pixelsPerRow = ByteBuffer.wrap(pixelsPerRowBytes).getInt();
        System.out.println(pixelsPerRow);



//        System.out.println(ByteBuffer.wrap(pixelsPerRowBytes).getInt());


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
