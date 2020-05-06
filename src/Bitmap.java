import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Bitmap {

    final int pixelDataOffset = 67108918;

    byte[] data;
    Integer width;
    Integer height;


    public Bitmap(ByteArrayOutputStream bmpBytes) {
        this.data = bmpBytes.toByteArray();
    }

    public int getWidth() {
        if (this.width == null) {
            byte[] bytes = Arrays.copyOfRange(this.data, 18, 22);
            reverseArray(bytes);
            this.width = ByteBuffer.wrap(bytes).getInt();
        }
        return this.width;
    }

    public int getHeight() {
        if (this.height == null) {
            byte[] bytes = Arrays.copyOfRange(this.data, 22, 26);
            reverseArray(bytes);
            this.height = ByteBuffer.wrap(bytes).getInt();
        }
        return this.height;
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
