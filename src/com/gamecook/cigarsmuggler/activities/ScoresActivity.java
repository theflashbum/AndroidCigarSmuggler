package com.gamecook.cigarsmuggler.activities;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import com.gamecook.cigarsmuggler.R;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 3:27:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScoresActivity extends TabActivity {

    private TabHost mTabHost;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Easy").setContent(R.id.textview1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Medium").setContent(R.id.textview2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Hard").setContent(R.id.textview3));

        mTabHost.setCurrentTab(0);

    }

    public void displayScores() {
        int total = 10;
        for (int i = 0; i < total; i++) {
            //scoresText.append( (i+1) + " Name: \n Score: \n");
        }
    }
}
