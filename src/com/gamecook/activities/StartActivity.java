package com.gamecook.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.gamecook.CigarSmugglerGame;
import com.gamecook.R;
import com.gamecook.fit.managers.SingletonManager;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 2:47:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener {

    private CigarSmugglerGame game;
    private static final int TOTAL_DAYS = 30;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);

        setContentView(R.layout.main);

        
        /* Create button handlers */

        configureStartButton();



        Button scores = (Button) findViewById(R.id.Scores);
        scores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ScoresActivtiy.class);
                startActivityForResult(myIntent, 0);
            }

        });

        Button instructions = (Button) findViewById(R.id.Instructions);
        instructions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), InstructionsActivtiy.class);
                startActivityForResult(myIntent, 0);
            }

        });

        Button credits = (Button) findViewById(R.id.Credits);
        credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CreditsActivtiy.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void configureStartButton() {

        Button start = (Button) findViewById(R.id.Start);


        // Test to see if the Game Instance has been started. If so change
        // the text of the button
        if(game.getGameStarted())
        {
            start.setText("Continue Game");
        }
        
        start.setOnClickListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        configureStartButton();
    }

    final CharSequence[] difficultLevels = {"Easy (90 days)", "Medium (60 Days)", "Hard (30 Days)"};

    public void chooseDifficulty() {

        if(game.getGameStarted())
        {
            gotoGameScreen();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a difficulty");

            builder.setItems(difficultLevels, this);
            AlertDialog alert = builder.create();
            builder.show();
        }
    }

    public void onClick(View view) {
        chooseDifficulty();
    }

    public void onClick(DialogInterface dialogInterface, int i) {

        Toast.makeText(getApplicationContext(), "Starting New Game!", Toast.LENGTH_SHORT).show();
        createNewGame(((difficultLevels.length - i) + 1) * TOTAL_DAYS);

    }

    private void createNewGame(int days) {
        game.startGame(days);

        gotoGameScreen();
    }

    private void gotoGameScreen() {
        Intent myIntent = new Intent(getApplicationContext(), GameActivity.class);
        startActivityForResult(myIntent, 0);
    }

}
