package ARPipeline;

import android.view.TextureView;

/// ARPipeline - a basic parent class for AR Systems to use in order to receive image frames and broadcast output
public abstract class ARPipeline implements Runnable{

    // flag to indicate if the system is running or not
    protected boolean running = false;

    protected TextureView outputTexture;

    protected int width;
    protected int height;

    public ARPipeline() {}

    public ARPipeline(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ARPipeline(int width, int height, TextureView outputTexture) {
        this.width = width;
        this.height = height;
        this.outputTexture = outputTexture;
    }

    // give a new frame to the AR system
    public abstract void addFrame(Frame frame);

    public void run() {
        this.start();
    }

    public void start() {
        this.running = true;
        this.mainLoop();
    }

    public void stop() {
        this.running = false;
    }

    public abstract void mainLoop();

    public TextureView getOutputTexture() {
        return outputTexture;
    }

    public void setOutputTexture(TextureView outputTexture) {
        this.outputTexture = outputTexture;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
