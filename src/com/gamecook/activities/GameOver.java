package com.gamecook.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.gamecook.CigarSmugglerGame;
import com.gamecook.R;
import com.gamecook.fit.managers.SingletonManager;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 23, 2010
 * Time: 9:33:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameOver extends Activity {

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);

        game.endGame();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent myIntent = new Intent(getApplicationContext(), StartActivity.class);
            startActivityForResult(myIntent, 0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
