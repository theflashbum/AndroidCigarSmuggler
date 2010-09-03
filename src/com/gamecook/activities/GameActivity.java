package com.gamecook.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.gamecook.core.CigarSmugglerGame;
import com.gamecook.R;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.intents.ViewCigarIntent;
import com.gamecook.items.Cigar;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 17, 2010
 * Time: 7:07:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener, AdapterView.OnItemClickListener {

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);
    private String[] cigarNames;
     private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private ViewFlipper viewFlipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Button location = (Button) findViewById(R.id.location);
        location.setOnClickListener(this);

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);

        slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
        slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
        slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);


        refreshDisplay();


    }

    public void onClick(View view) {
        chooseNewLocation();
    }


    /**
     * Loads the previous image
     */
    public void showCigarsView() {
        /*if (currentID - 1 >= 0) {
            currentID--;
            getNextImageView().setImageBitmap(largeImages[currentID]);*/

            viewFlipper.setInAnimation(slideRightIn);
            viewFlipper.setOutAnimation(slideRightOut);
            viewFlipper.showPrevious();

        //}
    }

    /**
     * Loads the next image
     * @param cigarID
     */
    public void showShopView(int cigarID) {

            // Display Cigar Info

            Cigar cigar = (Cigar) game.getStore().get(cigarNames[cigarID]);

            TextView name = (TextView) findViewById(R.id.title);
            name.setText(cigar.getName());

            TextView description = (TextView) findViewById(R.id.description);
            description.setText(cigar.getDescription());
            

        /*if (currentID + 1 < totalImages) {
            currentID++;
            getNextImageView().setImageBitmap(largeImages[currentID]);*/

            viewFlipper.setInAnimation(slideLeftIn);
            viewFlipper.setOutAnimation(slideLeftOut);
            viewFlipper.showNext();



        //}
    }

    private void chooseNewLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Where do you want to go?");

         // Create Location List
       // String location = game.getLocations().getLocationArray();
       String[] locations = {
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
                                                                   
        ListView itemsList =(ListView)findViewById(R.id.items);

        cigarNames = game.getStore().getInventoryAsArray();

        // By using setAdpater method in listview we an add string array in list.
        itemsList.setAdapter(new ArrayAdapter<String>(this,R.layout.cigar_item_1 , cigarNames));
        itemsList.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        showShopView(i);
        /*Intent myIntent = new ViewCigarIntent(getApplicationContext(), ItemDetailActivity.class, cigarNames[i]);

        startActivityForResult(myIntent, 0);*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && viewFlipper.getDisplayedChild() == 1) {
            showCigarsView();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
