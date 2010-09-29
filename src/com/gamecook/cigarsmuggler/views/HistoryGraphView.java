package com.gamecook.cigarsmuggler.views;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 18, 2010
 * Time: 7:59:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoryGraphView extends View {

    private int minY;
    private double maxY;
    private int graphHeight = 14;
    private int graphBarWidth = 4;
    private int graphBarPadding = 2;
    private ArrayList<Double> values;

    public HistoryGraphView(Context context) {
        super(context);
    }

    public HistoryGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HistoryGraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
     protected void onDraw(Canvas canvas) {
        if(values != null)
        {
            canvas.drawColor(0x00000000);

            Paint myPaint = new Paint();
            int total = values.size();
            Double value;
            int nextX = 0;
            int barHeight;
            int barBottom;
            Double lastValue = 0.0;

            int barWidth = graphBarWidth + graphBarPadding;
            int maxBars = getWidth()/barWidth;

            int startIndex = total > maxBars ? (total - maxBars) : 0;

            for(int i = startIndex; i < total; i ++)
            {

                value = values.get(i);

                barHeight = (graphHeight - (int)(graphHeight * ((double)value / (double)maxY))) + 1;
                barBottom = graphHeight;

                if(i == total-1)
                {
                    myPaint.setColor(lastValue < value ? 0xFF48c61b : 0xFFd15d5d);
                }
                else if (value < 2)
                {
                    myPaint.setColor(0xFF9c9a9c);
                }
                else
                {
                    myPaint.setColor(0xFFFFFFFF);
                }


                canvas.drawRect(nextX, barHeight, nextX+graphBarWidth, barBottom, myPaint);
                nextX = barWidth * ((i-startIndex)+1);
                lastValue = value;


            }
        }
//        super.draw(canvas);
    }

    public void generateGraph(ArrayList<Double> values)
    {
        this.values = values;
        indexValues(values);

        invalidate();
    }

    private void indexValues(ArrayList<Double> values) {
        int total = values.size();

        minY = 0;
        maxY = 0;

        for (int i = 0; i < total; i++)
        {
            if(values.get(i) > maxY)
                maxY = values.get(i);
        }
    }

}
