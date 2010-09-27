package com.gamecook.cigarsmuggler.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 26, 2010
 * Time: 9:41:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class CigarSmugglerButton extends Button{
    public CigarSmugglerButton(Context context) {
        super(context);
        init();
    }

    public CigarSmugglerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CigarSmugglerButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init()
    {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue.ttf");
        setTypeface(tf);
    }
}
