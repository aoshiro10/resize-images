import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class ImageResize {
    public static void main(String[] args) throws IOException {

        File inPath = new File("test.jpg");

        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        ImageIO.write(jpgImage, "BMP", bmpBytes);

        Bitmap bitmap = new Bitmap(bmpBytes);

        System.out.println(bitmap.getHeight());
        System.out.println(bitmap.getWidth());

    }
}
