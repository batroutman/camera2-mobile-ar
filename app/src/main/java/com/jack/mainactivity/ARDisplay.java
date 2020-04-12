package com.jack.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ARDisplay extends View {

    public Bitmap currentBitmap;
    private long lastDrawTime = System.nanoTime();

    public ARDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override()
    public void onDraw(Canvas canvas) {
        if (this.currentBitmap == null) return;

        long start = System.nanoTime();

        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(this.currentBitmap, 0, 0, this.currentBitmap.getWidth(), this.currentBitmap.getHeight(), matrix, true);
        Rect src = new Rect(0, 0, rotatedBitmap.getWidth(), rotatedBitmap.getHeight());
        Rect dst = new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        canvas.drawBitmap(rotatedBitmap, src, dst, new Paint());

        long stop = System.nanoTime();
        double time = 1000000000.0 / (stop - start);
        Log.v("onDraw", "Execution time for onDraw: " + time + "fps");

        long current = System.nanoTime();
        double diff = 1000000000.0 / (current - this.lastDrawTime);
        this.lastDrawTime = current;
        Log.v("DRAW_TIME", "Draw time: " + diff + "fps");

    }

}
