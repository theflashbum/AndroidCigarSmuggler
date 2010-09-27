package com.gamecook.cigarsmuggler.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 26, 2010
 * Time: 7:19:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerTextView extends TextView{
    public CigarSmugglerTextView(Context context) {
        super(context);
        init();
    }

    public CigarSmugglerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CigarSmugglerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    protected void init()
    {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue.ttf");
        setTypeface(tf);
    }
}
