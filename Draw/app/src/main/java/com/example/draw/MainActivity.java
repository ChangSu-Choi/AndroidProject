package com.example.draw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECT= 3;
    static int curShape = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemline:
                curShape = LINE;
                return true;

            case R.id.itemcircle:
                curShape = CIRCLE;
                return true;

            case R.id.itemrect:
                curShape = RECT;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        Bitmap resizedRed, resizedBlue, resizedBlack;
        Paint paint = new Paint();
        ArrayList<DrawShape> drawshape = new ArrayList<>();
        int shapeIdx = 0;

        public MyGraphicView(Context context) {
            super(context);
//            이미지를 리사이즈 시켜줘야함
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;

            Bitmap redbtn = BitmapFactory.decodeResource(getResources(), R.drawable.redbtn);
            resizedRed = Bitmap.createScaledBitmap( redbtn, 300, 300, true );

            Bitmap bluebtn = BitmapFactory.decodeResource(getResources(), R.drawable.bluebtn);
            resizedBlue = Bitmap.createScaledBitmap( bluebtn, 300, 300, true );

            Bitmap blackbtn = BitmapFactory.decodeResource(getResources(), R.drawable.blackbtn);
            resizedBlack = Bitmap.createScaledBitmap( blackbtn, 300, 300, true );

        }

        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    //        비트맵이 클릭 될 시 색상 변경
                    if(startX > 0 && startX < this.getWidth()/3 && startY > this.getHeight() - resizedRed.getHeight())
                        paint.setColor(Color.RED);
                    if(startX >= this.getWidth()/3 && startX < this.getWidth()/3*2 && startY > this.getHeight() - resizedRed.getHeight())
                        paint.setColor(Color.BLUE);
                    if(startX > this.getWidth()/3*2 && startX < this.getWidth() && startY > this.getHeight() - resizedRed.getHeight())
                        paint.setColor(Color.BLACK);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("choi", "move:x=" + event.getX() + " y=" + event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();

                    drawshape.add(new DrawShape(curShape, startX, startY, stopX, stopY, paint));
                    shapeIdx++;

                    this.invalidate();
                    break;
            }



            return true;
        }


        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);



            for (int i = 0; i < shapeIdx; i++){
                draw_shape(drawshape.get(i), canvas);
            }


//            버튼의 위치 설정후 표시
            int picX = (this.getWidth() - resizedRed.getWidth());
            int picY = (this.getHeight() - resizedRed.getHeight());
            canvas.drawBitmap(resizedRed, 0, picY, null);
            canvas.drawBitmap(resizedBlue, picX/2, picY, null);
            canvas.drawBitmap(resizedBlack, picX, picY, null);

        }
        public void draw_shape(DrawShape Shape, Canvas canvas){

            System.out.println(shapeIdx);
            curShape = Shape.curShape;
            startX = Shape.startX;
            startY = Shape.startY;
            stopX = Shape.stopX;
            stopY = Shape.stopY;
            paint = Shape.paint;

            switch (curShape) {
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopX - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECT:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
            }
        }

    }

    private static class DrawShape {
        int curShape, startX, startY, stopX, stopY;
        Paint paint;

        public DrawShape(int curShape, int startX, int startY, int stopX, int stopY, Paint paint) {
            this.curShape = curShape;
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
            this.paint = paint;

            }
        }
    }


