import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Bitmap {

    byte[] data;

    int pixelDataOffset;
    int width;
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
    }

    public void removeColor() {
        for (int i = pixelDataOffset; i < data.length; i++) {
            data[i] = 0;
        }
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
