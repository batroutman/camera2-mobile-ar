package ARPipeline;

import android.view.TextureView;
import android.view.View;

import com.jack.mainactivity.ARDisplay;

/// ARPipeline - a basic parent class for AR Systems to use in order to receive image frames and broadcast output
public abstract class ARPipeline implements Runnable{

    // flag to indicate if the system is running or not
    protected boolean running = false;

    protected ARDisplay outputView;

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
        this.outputView = outputView;
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

    public View getOutputView() {
        return outputView;
    }

    public void setOutputView(ARDisplay outputView) {
        this.outputView = outputView;
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
