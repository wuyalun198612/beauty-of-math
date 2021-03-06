package owo.bom.decor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by wangli on 10/21/16.
 */

public class CircleDecorView extends View {
  public CircleDecorView(Context context) {
    super(context);
    mPaint.setAntiAlias(true);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  Paint mPaint = new Paint();
  Path mPath = new Path();

  private double mPreRx, mPreRy;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    int ox = getWidth() / 2;
    int oy = getHeight() / 2;

    mPath.reset();
    double step_angle = Math.PI / 20;
    int R = 400;
    for (int i = 0; i < 100; i++) {
      double angle = i * step_angle;
      double Rx1 = ox + R * Math.cos(angle);
      double Ry1 = oy + R * Math.sin(angle);
      if (mPath.isEmpty()) {
        mPath.moveTo((float) Rx1, (float) Ry1);
      } else {
        double edge = Math.sqrt((Rx1 - mPreRx) * (Rx1 - mPreRx) + (Ry1 - mPreRy) * (Ry1 - mPreRy));
        mPath.addCircle((float) (mPreRx + Rx1) / 2,
                        (float) (mPreRy + Ry1) / 2,
                        (float) edge / 2,
                        Path.Direction.CCW);
      }
      mPreRx = Rx1;
      mPreRy = Ry1;
    }

    canvas.drawPath(mPath, mPaint);
  }
}
