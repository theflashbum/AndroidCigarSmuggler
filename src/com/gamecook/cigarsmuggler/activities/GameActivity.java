package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.gamecook.cigarsmuggler.core.CigarSmugglerGame;
import com.gamecook.R;
import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.quietlycoding.android.picker.NumberPicker;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 17, 2010
 * Time: 7:07:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener, AdapterView.OnItemClickListener, NumberPicker.OnChangedListener {

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);
    private String[] cigarNames;
     private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private ViewFlipper viewFlipper;
    private Button buyButton;
    private Button sellButton;
    private static final int BUY = 1;
    private static final int SELL = 2;
    private TextView shopTotal;
    private Cigar currentCigar = null;
    private Inventory inventory;
    private int tmpTotalToBuy;
    private int currentShopMode;

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    private int currentLocation;

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

        buyButton = (Button) findViewById(R.id.buy);
        buyButton.setOnClickListener(this);

        sellButton = (Button) findViewById(R.id.sell);
        sellButton.setOnClickListener(this);


        inventory = (Inventory) SingletonManager.getInstance().getClassReference(Inventory.class);

        refreshDisplay();


    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id)
        {
            case R.id.buy:
                createShopPopup(BUY);
                break;
            case R.id.sell:
                createShopPopup(SELL);
                break;
            case R.id.location:
                chooseNewLocation();
                break;
        }


    }

    private void createShopPopup(int type) {
        //Context mContext = getApplicationContext();
        currentShopMode = type;
        String action = type == BUY ? "Buy" : "Sell";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setMessage(action+" "+currentCigar.getName())
       .setCancelable(false)
       .setPositiveButton(action, new BuyClickHandler())
       .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }
       });
AlertDialog alert = builder.create();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.purchase_alert, null);

        alert.setView(layout);

        TextView description = (TextView) layout.findViewById(R.id.PurchaseDescription);
        description.setText("This cigar is valued at "+currentCigar.getPrice()+" How many would you like to "+action.toLowerCase()+"?");

        shopTotal = (TextView) layout.findViewById(R.id.PurchaseTotalText);


        NumberPicker numberPicker = (NumberPicker) layout.findViewById(R.id.PurchaseNumberPicker);

        if(type == BUY)
        {

            // Configure range and current value
            int maxPurchase = (int) Math.floor( game.getWallet().getTotal() / currentCigar.getPrice() );

            numberPicker.setRange(1, maxPurchase);
            numberPicker.setCurrent(maxPurchase);

            numberPicker.setOnChangeListener(this);
            updateBuyValue(maxPurchase);

        }
        else
        {
            // Configure range and current value
            int maxSell = (int) Math.floor( inventory.getItemTotal(currentCigar.getName()));

            numberPicker.setRange(1, maxSell);
            numberPicker.setCurrent(maxSell);

            numberPicker.setOnChangeListener(this);
            updateBuyValue(maxSell);
        }
        alert.show();

    }

    private void configureDialog(Dialog target, int type) {

        if (type == BUY)
        {
            target.setTitle("Buy Cigar");
        }else if (type == SELL)
        {
            target.setTitle("Sell Cigar");
        }


    }


    /**
     * Loads the previous image
     */
    public void showCigarsView() {
        viewFlipper.setInAnimation(slideRightIn);
        viewFlipper.setOutAnimation(slideRightOut);
        viewFlipper.showPrevious();
    }

    /**
     * Loads the next image
     * @param cigarID
     */
    public void showShopView(int cigarID) {

            // Display Cigar Info

            currentCigar = (Cigar) game.getStore().get(cigarNames[cigarID]);

        toggleSellButton();

        TextView name = (TextView) findViewById(R.id.title);
            name.setText(currentCigar.getName());

            TextView description = (TextView) findViewById(R.id.description);
            description.setText(currentCigar.getDescription());

            viewFlipper.setInAnimation(slideLeftIn);
            viewFlipper.setOutAnimation(slideLeftOut);
            viewFlipper.showNext();

    }

    public void onBuySellUpdate()
    {
        toggleSellButton();
        refreshDisplay();
    }

    private void toggleSellButton() {
        if(inventory.hasItem(currentCigar.getName()))
        {
            //sellButton.setVisibility(View.VISIBLE);
            sellButton.setEnabled(true);
        }
        else
        {
            sellButton.setEnabled(false);
            //sellButton.setVisibility(View.INVISIBLE);
        }

        int maxPurchase = (int) Math.floor( game.getWallet().getTotal() / currentCigar.getPrice() );
        if(maxPurchase > 0)
            buyButton.setEnabled(true);
        else
            buyButton.setEnabled(false);

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

        builder.setItems(locations, new LocationSelectionListener());
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void nextTurn()
    {
        //TODO add in logic to change prices and refresh screen

        resetView();

        if(game.getCalendar().hasNextDay())
        {
            Toast.makeText(getApplicationContext(), "New Day", Toast.LENGTH_SHORT).show();
            
            game.nextTurn();
            
            Intent myIntent = new Intent(getApplicationContext(), GameActivity.class);

            startActivityForResult(myIntent, 0);
        }
        else
        {


            Toast.makeText(getApplicationContext(), "You're Out Of Time!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }

    private void resetView() {
        if(currentCigar != null)
        {
            currentCigar = null;
            showCigarsView();
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

    public void onChanged(NumberPicker picker, int oldVal, int newVal) {
        updateBuyValue(newVal);
    }

    private void updateBuyValue(int value) {
        tmpTotalToBuy = value;
        shopTotal.setText("Total $"+Double.toString(currentCigar.getPrice() * value));
    }

    /**
     * Click handler for changing locations
     */
    private class LocationSelectionListener implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialogInterface, int i) {
            GameActivity.this.setCurrentLocation(i);
            GameActivity.this.nextTurn();
        }

    }


    private class BuyClickHandler implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialogInterface, int i) {
            if(currentShopMode == BUY)
            {
                game.getWallet().subtract(tmpTotalToBuy * currentCigar.getPrice());
                inventory.add(currentCigar, tmpTotalToBuy);
            }
            else
            {
                game.getWallet().add(tmpTotalToBuy * currentCigar.getPrice());
                inventory.removeFromInventory(currentCigar, tmpTotalToBuy);
            }

            GameActivity.this.onBuySellUpdate();
        }
    }
}
