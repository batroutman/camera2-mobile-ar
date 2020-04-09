package com.jack.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ARDisplay extends View {

    public Bitmap currentBitmap;

    public ARDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override()
    public void onDraw(Canvas canvas) {

        if (this.currentBitmap == null) return;

        canvas.drawBitmap(this.currentBitmap, 0, 0, new Paint());

    }

}
