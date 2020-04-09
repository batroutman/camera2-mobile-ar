package ARPipeline;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.TextureView;

public class TestARPipeline extends ARPipeline {

    public final int MIN_FRAMETIME = 16;

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

    public TestARPipeline(int width, int height) {
        super(width, height);
    }

    public TestARPipeline(int width, int height, TextureView outputTexture) {
        super(width, height, outputTexture);
    }

    @Override
    public void addFrame(Frame frame) {

        if (this.acceptingFrames) {
            Log.v("TestARPipeline", "acceptingFrames");
            this.acceptingFrames = false;
            synchronized (this.currentFrameLock) {
                this.currentFrame = frame;
            }
        } else {
            Log.v("TestARPipeline", "NOT acceptingFrames");
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

    // generate image and draw to outputTexture
    private void drawImage() {
        int numPixels = this.currentFrame.getY().length;
        int [] totalData = new int[numPixels];
        for (int i = 0; i < numPixels; i++) {
            byte pixelVal = this.currentFrame.getY()[i];
            byte A = (byte) 255;
            byte R = pixelVal;
            byte G = pixelVal;
            byte B = pixelVal;
            int color = (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 8 | (B & 0xff);

            totalData[i] = color;
        }
        Bitmap bitmap = Bitmap.createBitmap(totalData, this.width, this.height, Bitmap.Config.RGBA_F16);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        this.outputView.currentBitmap = bitmap;
    }

    @Override
    public void mainLoop() {

        while (this.running) {

            synchronized (this.currentFrameLock) {
                if (this.currentFrame != null) {

                    this.drawImage();

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
