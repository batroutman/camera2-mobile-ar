package ARPipeline;

public class TestARPipeline extends ARPipeline {

    public final int MIN_FRAMETIME = 5;

    //////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////        PROPERTIES        /////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    // flag to determine if the system is currently accepting frames, or if incoming
    // frames should be redirected to the on-deck frame. If true, an incoming frame will
    // be placed into the currentFrame property. Otherwise, the frame will be placed into
    // the onDeckFrame property (synchronized to alleviate race conditions).
    private boolean acceptingFrames = true;

    // the current frame that the system is operating on and its lock.
    private Frame currentFrame;
    private Object currentFrameLock = new Object();

    // the next frame to take the place of currentFrame when operations on currentFrame
    // are completed.
    private Frame onDeckFrame;


    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////       METHODS        ///////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public TestARPipeline() {
        super();
    }

    @Override
    public void addFrame(Frame frame) {

        if (this.acceptingFrames) {
            this.acceptingFrames = false;
            synchronized (this.currentFrameLock) {
                this.currentFrame = frame;
            }
        } else {
            this.onDeckFrame = frame;
        }

    }

    // set currentFrame to onDeckFrame and nullify onDeckFrame
    private void rotateFrames() {
        synchronized (this.currentFrameLock) {
            this.currentFrame = this.onDeckFrame;
            this.onDeckFrame = null;
            if (this.currentFrame == null) {
                this.acceptingFrames = true;
            }
        }
    }

    @Override
    public void mainLoop() {

        while (this.running) {

            synchronized (this.currentFrameLock) {
                if (this.currentFrame != null) {



                }
            }

            this.rotateFrames();

            try {
                Thread.sleep(this.MIN_FRAMETIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
