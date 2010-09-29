package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.gamecook.cigarsmuggler.R;
import com.gamecook.cigarsmuggler.core.CigarSmugglerGame;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.fit.util.MoneyToStringUtil;
import com.openfeint.api.resource.Leaderboard;
import com.openfeint.api.resource.Score;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 23, 2010
 * Time: 9:33:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameOverActivity extends Activity implements View.OnClickListener {

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        game.endGame();

        Button submit = (Button) findViewById(R.id.SubmitScore);
        submit.setOnClickListener(this);
        refreshDisplay();
    }

    private void refreshDisplay() {

        ((TextView) findViewById(R.id.DifficultyText)).setText(game.difficultyToString());


        ((TextView) findViewById(R.id.ScoreText)).setText(Integer.toString(game.getScore()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent myIntent = new Intent(getApplicationContext(), StartActivity.class);
            startActivityForResult(myIntent, 0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void submitScore() {
        String scoreBoardID;

        switch (game.getCalendar().getTotalDays()) {
            default:
                scoreBoardID = "498393";
        }

        int scoreValue = game.getScore();
        Score s = new Score(scoreValue, null); // Second parameter is null to indicate that custom display text is not used.
        Leaderboard l = new Leaderboard(scoreBoardID);
        s.submitTo(l, new Score.SubmitToCB() {
            @Override
            public void onSuccess(boolean newHighScore) {
                // sweet, pop the thingerydingery
                /*GameOverActivity.this.setResult(Activity.RESULT_OK);
                GameOverActivity.this.finish();*/
                Intent myIntent = new Intent(getApplicationContext(), StartActivity.class);
                startActivityForResult(myIntent, 0);
            }

            @Override
            public void onFailure(String exceptionMessage) {
                Toast.makeText(GameOverActivity.this,
                        "Error (" + exceptionMessage + ") posting score.",
                        Toast.LENGTH_SHORT).show();
                /*GameOverActivity.this.setResult(Activity.RESULT_CANCELED);
                GameOverActivity.this.finish();*/
            }
        });
    }

    public void onClick(View view) {
        submitScore();
    }
}
