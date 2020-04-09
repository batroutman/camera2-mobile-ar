package ARPipeline;

public class Frame {

    //////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////        PROPERTIES        /////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    // timestamp of the time the frame was generated (nanoseconds)
    private long time;

    // byte array of Y channel of image (YUV)
    private byte [] y;

    // byte array of U channel of image (YUV)
    private byte [] u;

    // byte array of V channel of image (YUV)
    private byte [] v;

    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////       METHODS        ///////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public Frame() {
        this.time = System.nanoTime();
    }

    public Frame(byte [] y) {
        this.time = System.nanoTime();
        this.y = y;
    }

    public Frame(byte [] y, byte [] u, byte [] v) {
        this.time = System.nanoTime();
        this.y = y;
        this.u = u;
        this.v = v;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public byte[] getY() {
        return y;
    }

    public void setY(byte[] y) {
        this.y = y;
    }

    public byte[] getU() {
        return u;
    }

    public void setU(byte[] u) {
        this.u = u;
    }

    public byte[] getV() {
        return v;
    }

    public void setV(byte[] v) {
        this.v = v;
    }
}
