package ARPipeline;

/// ARPipeline - a basic parent class for AR Systems to use in order to receive image frames and broadcast output
public abstract class ARPipeline {

    // flag to indicate if the system is running or not
    protected boolean running = false;

    public ARPipeline() {}

    // give a new frame to the AR system
    public abstract void addFrame(Frame frame);

    public void start() {
        this.running = true;
        this.mainLoop();
    }

    public void stop() {
        this.running = false;
    }

    public abstract void mainLoop();

}
