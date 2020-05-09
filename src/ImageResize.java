import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class ImageResize {
    public static void main(String[] args) throws IOException {

        File inPath = new File("test.jpg");

        BufferedImage jpgImage = ImageIO.read(inPath);
        ByteArrayOutputStream bmpBytes = new ByteArrayOutputStream();
        ImageIO.write(jpgImage, "BMP", bmpBytes);

        Bitmap bitmap = new Bitmap(bmpBytes.toByteArray());

        Bitmap tempBitmap = bitmap.resize((int) (bitmap.width*.5), (int) (bitmap.height*1.5));




//        for (int i = 0; i < 54; i++) {
//            System.out.println("index: " + i);
//            System.out.println("old: " + bitmap.data[i]);
//            System.out.println("new: " + tempBitmap.data[i]);
//        }


        InputStream in = new ByteArrayInputStream(tempBitmap.data);
        BufferedImage bImageFromConvert = ImageIO.read(in);



        //bitmap.setPixel(pixel1, 2, 3);

        ImageIO.write(bImageFromConvert, "JPG", new File(
                "test1.jpg"));
        System.out.println("saved");


    }
}
