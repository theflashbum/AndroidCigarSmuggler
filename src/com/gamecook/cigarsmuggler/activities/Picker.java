package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.os.Bundle;
import com.gamecook.cigarsmuggler.R;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 26, 2010
 * Time: 11:19:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Picker extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker);
    }
}
