public class Pixel {

    byte red;
    byte green;
    byte blue;

    public Pixel(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    @Override
    public String toString() {
        return "Pixel{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
