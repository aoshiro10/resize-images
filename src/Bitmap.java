import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Bitmap {

    final static int BYTES_PER_PIXEL = 3;
    final static int LINE_PADDING = 4;

    byte[] data;

    int bytesPerRow;

    int pixelDataOffset;
    // in pixels
    int width;
    // in pixels
    int height;
    int size;
    short bpp;


    public static Bitmap emptyBitmap(int width, int height) {
        int headerSize = 14;
        int infoHeaderSize = 40;
        int endPadding = mod(LINE_PADDING - mod(width * BYTES_PER_PIXEL, LINE_PADDING), LINE_PADDING);
        int rowSize = width * BYTES_PER_PIXEL + endPadding;
        int pixelDataSize = rowSize * height;
        byte[] data = new byte[headerSize+infoHeaderSize+pixelDataSize];

        buildHeader(data, headerSize+infoHeaderSize);
        buildInfoHeader(data, width, height);

        return new Bitmap(data);
    }




    public Bitmap resize(int newWidth, int newHeight) {

        Bitmap tempBitmap = Bitmap.emptyBitmap(newWidth, this.height);
        float dWidth = ((float) this.width)/newWidth;

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < newWidth; col++) {
                int oCol = Math.round(dWidth*col);
                Pixel pixel = this.getPixel(row, oCol);
                tempBitmap.setPixel(pixel, row, col);
            }
        }

        Bitmap finalBitmap = Bitmap.emptyBitmap(newWidth, newHeight);

        float dHeight = ((float) this.height)/newHeight;

        for (int row = 0; row < newHeight; row++) {
            for (int col = 0; col < newWidth; col++) {
                int oRow = Math.round(dHeight*row);
                Pixel pixel = tempBitmap.getPixel(oRow, col);
                finalBitmap.setPixel(pixel, row, col);
            }
        }

        return finalBitmap;
    }

    private static byte[] intToBytes(int v) {
        return ByteBuffer.allocate(4).putInt(v).array();
    }

    private static void buildInfoHeader(byte[] data, int width, int height) {
        // Size
        data[14] = 0x28;

        // Width
        byte[] widthBytes = intToBytes(width);
        reverseArray(widthBytes);
        System.arraycopy(widthBytes, 0, data, 18, 4);

        // Height
        byte[] heightBytes = intToBytes(height);
        reverseArray(heightBytes);
        System.arraycopy(heightBytes, 0, data, 22, 4);

        // Planes
        data[26] = 0x01;

        // Bits Per Pixel
        data[28] = 0x18;

    }

    private static void buildHeader(byte[] data, int dataOffset) {
        // Signature
        data[0] = 'B';
        data[1] = 'M';

        // FileSize
        byte[] fileSizeBytes = intToBytes(data.length);
        reverseArray(fileSizeBytes);
        System.arraycopy(fileSizeBytes, 0, data, 2, 4);

        // Data Offset
        byte[] dataOffsetBytes = intToBytes(dataOffset);
        reverseArray(dataOffsetBytes);
        System.arraycopy(dataOffsetBytes, 0, data, 10, 4);
    }


    public Bitmap(byte[] data) {
        this.data = data;
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

    public void removeBlue() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel tempPixel = getPixel(row, col);
                tempPixel.blue = 0;
                setPixel(tempPixel, row, col);
            }
        }
    }

    private static int mod(int n, int d) {
        return ((n % d) + d) % d;
    }

    /*
    Bottom left to Top Corner
     */
    public Pixel getPixel(int row, int col) {
        int index = getPixelIndex(row, col);
        byte blue = this.data[index];
        byte green = this.data[index+1];
        byte red = this.data[index+2];
        return new Pixel(red, green, blue);
    }

    public void setPixel(Pixel pixel, int row, int col) {
        int index = getPixelIndex(row, col);

        this.data[index] = pixel.blue;
        this.data[index + 1] = pixel.green;
        this.data[index + 2] = pixel.red;
    }

    private int getPixelIndex(int row, int col) {
        return pixelDataOffset + row * bytesPerRow + col * BYTES_PER_PIXEL;

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

    private static void reverseArray(byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            int iVerse = bytes.length - i - 1;
            byte temp = bytes[iVerse];
            bytes[iVerse] = bytes[i];
            bytes[i] = temp;
        }
    }



}
