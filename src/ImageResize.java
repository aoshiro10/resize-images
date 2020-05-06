import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;

public class ImageResize {
    public static void main(String[] args) throws IOException {

        File inPath = new File("test.jpg");

        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        ImageIO.write(jpgImage, "BMP", bmpBytes);

        Bitmap bitmap = new Bitmap(bmpBytes);

        bitmap.removeColor();

        InputStream in = new ByteArrayInputStream(bitmap.data);
        BufferedImage bImageFromConvert = ImageIO.read(in);

        ImageIO.write(bImageFromConvert, "BMP", new File(
                "test1.bmp"));
        System.out.println("saved");


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
