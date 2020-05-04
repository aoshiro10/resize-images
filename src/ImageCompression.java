import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageCompression {
    public static void main(String[] args) throws IOException {
        BufferedImage bufferimage = ImageIO.read(new File("test.jpg"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferimage, "jpg", output );
        byte [] data = output.toByteArray();
        String base64 = new String(data);
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("output.jpg") );
        System.out.println("image created");
        System.out.println(base64.length());
//        System.out.println(base64);
    }
}
