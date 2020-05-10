import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class ImageResize {
    public static void main(String[] args) throws IOException {

        File inPath = new File(args[0]);
        File outPath = new File(args[1]);

        int width = Integer.parseInt(args[2]);
        int height = Integer.parseInt(args[3]);

        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        ImageIO.write(jpgImage, "BMP", bmpBytes);

        Bitmap bitmap = new Bitmap(bmpBytes.toByteArray());

        InputStream in = new ByteArrayInputStream(bitmap.resize(width, height).data);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        ImageIO.write(bImageFromConvert, "JPG", outPath);

    }
}
