package com.gamecook.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.gamecook.CigarSmugglerGame;
import com.gamecook.R;
import com.gamecook.enums.Cigars;
import com.gamecook.fit.managers.SingletonManager;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 17, 2010
 * Time: 7:07:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener{

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Button location = (Button) findViewById(R.id.location);
        location.setOnClickListener(this);

        refreshDisplay();
    }

    public void onClick(View view) {
        chooseNewLocation();
    }

    private void chooseNewLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Where do you want to go?");

         // Create Location List

       CharSequence[] locations = {
            "Ft. Lauderdale",
            "Miami",
            "Hialeah",
            "Hollywood",
            "Boca Raton"};

        builder.setItems(locations, this);
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        nextTurn();
    }

    public void nextTurn()
    {
        //TODO add in logic to change prices and refresh screen

        if(game.getCalendar().hasNextDay())
        {
            Toast.makeText(getApplicationContext(), "New Day", Toast.LENGTH_SHORT).show();
            
            game.nextTurn();
            
            refreshDisplay();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "You're Out Of Time!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), GameOver.class);
            startActivityForResult(myIntent, 0);
        }
    }

    public void refreshDisplay()
    {
        TextView daysLabel = (TextView) findViewById(R.id.days);
        daysLabel.setText(game.getCalendar().getDays()+" Days Left");

        TextView cashLabel = (TextView) findViewById(R.id.cash);
        cashLabel.setText("Cash: "+game.getWallet().getTotal());
                                                                   
        String cigars[]={"Test1","Test2","Test3","Test4"};
        ListView itemsList =(ListView)findViewById(R.id.items);
        // By using setAdpater method in listview we an add string array in list.
        itemsList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , cigars));

    }
}
