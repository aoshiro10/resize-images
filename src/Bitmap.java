import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Bitmap {

    final int BYTES_PER_PIXEL = 3;
    final int LINE_PADDING = 4;

    byte[] data;

    int bytesPerRow;

    int pixelDataOffset;
    // in pixels
    int width;
    // in pixels
    int height;
    int size;
    short bpp;


    public Bitmap(ByteArrayOutputStream bmpBytes) {
        this.data = bmpBytes.toByteArray();
        this.width = getIntValue(18);
        this.height = getIntValue(22);
        this.size = getIntValue(2);
        this.bpp = getShortValue(28);
        this.pixelDataOffset = getIntValue(10);

        int endPadding = mod(LINE_PADDING - mod(width * BYTES_PER_PIXEL, LINE_PADDING), LINE_PADDING);
        this.bytesPerRow = width * BYTES_PER_PIXEL + endPadding;
    }

    public void removeColor() {
        for (int i = pixelDataOffset; i < data.length; i++) {
            data[i] = 0;
        }
    }

    private int mod(int n, int d) {
        return ((n % d) + d) % d;
    }

    /*
    Bottom left to Top Corner
     */
    public Pixel getPixel(int row, int col) {
        int index = pixelDataOffset + row * bytesPerRow + col;
        byte blue = this.data[index];
        byte green = this.data[index+1];
        byte red = this.data[index+2];
        return new Pixel(red, green, blue);
    }

    public void setPixel(Pixel pixel, int row, int col) {
        System.out.println(pixel);
        int index = pixelDataOffset + row * bytesPerRow + col;
        this.data[index] = pixel.blue;
        this.data[index + 1] = pixel.green;
        this.data[index + 2] = pixel.red;
    }

    private int getIntValue(int offset) {
        byte[] bytes = Arrays.copyOfRange(this.data, offset, offset+4);
        reverseArray(bytes);
        return ByteBuffer.wrap(bytes).getInt();
    }

    private short getShortValue(int offset) {
        byte[] bytes = Arrays.copyOfRange(this.data, offset, offset+2);
        reverseArray(bytes);
        return ByteBuffer.wrap(bytes).getShort();
    }

    private void reverseArray(byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            int iVerse = bytes.length - i - 1;
            byte temp = bytes[iVerse];
            bytes[iVerse] = bytes[i];
            bytes[i] = temp;
        }
    }



}
