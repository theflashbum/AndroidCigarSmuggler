package com.gamecook.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.gamecook.R;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 3:18:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class InstructionsActivtiy extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        TextView instructionText = (TextView) findViewById(R.id.instructionText);
        instructionText.setMovementMethod(new ScrollingMovementMethod());
    }
}
