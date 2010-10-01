package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.gamecook.cigarsmuggler.R;
import com.gamecook.cigarsmuggler.adapters.CigarAdapter;
import com.gamecook.cigarsmuggler.collections.CigarSmugglerLocations;
import com.gamecook.cigarsmuggler.core.CigarSmugglerGame;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.fit.util.MoneyToStringUtil;
import com.gamecook.cigarsmuggler.views.NumberPicker;

import java.io.IOException;
import java.io.InputStream;

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
    private Button buyButton;
    private Button sellButton;
    private static final int BUY = 1;
    private static final int SELL = 2;
    private TextView shopTotal;
    private Cigar currentCigar = null;
    private Inventory inventory;
    private int tmpTotalToBuy;
    private int currentShopMode;
    private String[] activeLocations;
    private ImageView locationImage;
    private int buySellMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game);

        locationImage = (ImageView) findViewById(R.id.LocationImage);
        locationImage.setOnClickListener(this);

        buyButton = (Button) findViewById(R.id.BuyButton);
        buyButton.setOnClickListener(this);

        sellButton = (Button) findViewById(R.id.SellButton);
        sellButton.setOnClickListener(this);

        inventory = game.getInventory();

        toggleBuySellMode(BUY);

        refreshDisplay();

    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.BuyButton:
                toggleBuySellMode(BUY);
                break;
            case R.id.SellButton:
                toggleBuySellMode(SELL);
                break;
            case R.id.LocationImage:
                chooseNewLocation();
                break;
        }

        refreshItemList();

    }

    private void createShopPopup(int type) {
        //Context mContext = getApplicationContext();
        currentShopMode = type;
        String action = type == BUY ? "Buy" : "Sell";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(action + " " + currentCigar.getName())
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
        description.setText("This cigar is valued at " + MoneyToStringUtil.convertToString(currentCigar.getPrice(), true) + ". How many would you like to " + action.toLowerCase() + "?");

        shopTotal = (TextView) layout.findViewById(R.id.PurchaseTotalText);


        NumberPicker numberPicker = (NumberPicker) layout.findViewById(R.id.PurchaseNumberPicker);

        if (type == BUY) {

            // Configure range and current value
            int maxPurchase = (int) Math.floor(game.getWallet().getTotal() / currentCigar.getPrice());

            if(maxPurchase > inventory.getTotalLeft())
                maxPurchase = inventory.getTotalLeft();

            numberPicker.setRange(1, maxPurchase);
            numberPicker.setCurrent(maxPurchase);

            numberPicker.setOnChangeListener(this);
            updateBuyValue(maxPurchase);

        } else {
            // Configure range and current value
            int maxSell = (int) Math.floor(inventory.getItemTotal(currentCigar.getName()));

            numberPicker.setRange(1, maxSell);
            numberPicker.setCurrent(maxSell);

            numberPicker.setOnChangeListener(this);
            updateBuyValue(maxSell);
        }
        alert.show();

    }

    private void configureDialog(Dialog target, int type) {

        if (type == BUY) {
            target.setTitle("Buy Cigar");
        } else if (type == SELL) {
            target.setTitle("Sell Cigar");
        }
    }

    public void onBuySellUpdate() {
        toggleSellButton();
        refreshDisplay();
    }

    private void toggleSellButton() {
        if (inventory.hasItem(currentCigar.getName())) {
            //sellButton.setVisibility(View.VISIBLE);
            sellButton.setEnabled(true);
        } else {
            sellButton.setEnabled(false);
            //sellButton.setVisibility(View.INVISIBLE);
        }

        int maxPurchase = (int) Math.floor(game.getWallet().getTotal() / currentCigar.getPrice());
        if (maxPurchase > 0)
            buyButton.setEnabled(true);
        else
            buyButton.setEnabled(false);

    }

    private void chooseNewLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Where do you want to go?");

        activeLocations = ((CigarSmugglerLocations) game.getLocations()).getActiveLocations();

        builder.setItems(activeLocations, new LocationSelectionListener());
        AlertDialog alert = builder.create();

        builder.show();
    }


    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void nextTurn() {
        //TODO add in logic to change prices and refresh screen

        resetView();

        if (game.getCalendar().hasNextDay()) {
            Toast.makeText(getApplicationContext(), "New Day", Toast.LENGTH_SHORT).show();

            game.nextTurn();

            Intent myIntent = new Intent(getApplicationContext(), GameActivity.class);

            startActivityForResult(myIntent, 0);
        } else {


            Toast.makeText(getApplicationContext(), "You're Out Of Time!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), GameOverActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }

    private void resetView() {
        if (currentCigar != null) {
            currentCigar = null;
            //showCigarsView();
        }
    }

    public void refreshDisplay() {

        // Days Left
        ((TextView) findViewById(R.id.DaysLeftText)).setText(Integer.toString(game.getCalendar().getDays()));

        // Days Total
        ((TextView) findViewById(R.id.DaysTotalText)).setText(Integer.toString(game.getCalendar().getTotalDays()));

        // Cash
        ((TextView) findViewById(R.id.CashText)).setText(MoneyToStringUtil.convertToString(game.getWallet().getTotal(), true));

        // Debt
        ((TextView) findViewById(R.id.DebtText)).setText(MoneyToStringUtil.convertToString(game.getBank().getLoan(), true));

        // Savings
        ((TextView) findViewById(R.id.SavingsText)).setText(MoneyToStringUtil.convertToString(game.getBank().getSavings(), true));


        refreshItemList();

        // This should always be done inside a try catch.
        // Also this img is at the root of assets.
        try {
            // Get reference to AssetManager
            AssetManager mngr = getAssets();

            String imageFileName = "location_image_" + game.getLocations().getCurrentLocationID() + ".png";

            // Create an input stream to read from the asset folder
            InputStream ins = mngr.open(imageFileName);

            // Convert the input stream into a bitmap
            Bitmap img = BitmapFactory.decodeStream(ins);

            locationImage.setImageBitmap(img);

        } catch (final IOException e) {
            e.printStackTrace();
        }


    }

    private void refreshItemList() {

        cigarNames = game.getStore().getInventoryAsArray();

        // By using setAdpater method in listview we an add string array in list.
        CigarAdapter adapter = new CigarAdapter(this, game.getStore(), game.getInventory(), game.getWallet().getTotal(), buySellMode == BUY ? "buy" : "sell");

        ListView itemsList = (ListView) findViewById(R.id.items);
        itemsList.setAdapter(adapter);
        itemsList.setOnItemClickListener(this);
        //itemsList.setDivider(null);
    }

    public void toggleBuySellMode(int mode) {

        buySellMode = mode;

        switch (mode) {
            case BUY:
                buyButton.setEnabled(false);
                buyButton.setTextColor(0x50ffffff);
                sellButton.setEnabled(true);
                sellButton.setTextColor(0xffffffff);
                break;
            case SELL:
                buyButton.setEnabled(true);
                buyButton.setTextColor(0xffffffff);
                sellButton.setEnabled(false);
                sellButton.setTextColor(0x50ffffff);
                break;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        currentCigar = (Cigar) game.getStore().get(cigarNames[i]);
        createShopPopup(buySellMode);
    }

    public void onChanged(NumberPicker picker, int oldVal, int newVal) {
        updateBuyValue(newVal);
    }

    private void updateBuyValue(int value) {
        tmpTotalToBuy = value;
        shopTotal.setText("Total " + MoneyToStringUtil.convertToString(currentCigar.getPrice() * value, true));
    }

    /**
     * Click handler for changing locations
     */
    private class LocationSelectionListener implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialogInterface, int i) {

            if (activeLocations[i] == "Bank") {
                Intent myIntent = new Intent(getApplicationContext(), Safe.class);

                startActivityForResult(myIntent, 0);
            } else {
                game.setCurrentLocation(activeLocations[i]);
                GameActivity.this.nextTurn();
            }

        }

    }

    private class BuyClickHandler implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (currentShopMode == BUY) {
                game.getWallet().subtract(tmpTotalToBuy * currentCigar.getPrice());
                inventory.add(currentCigar, tmpTotalToBuy);
            } else {
                game.getWallet().add(tmpTotalToBuy * currentCigar.getPrice());
                inventory.removeFromInventory(currentCigar, tmpTotalToBuy);
            }

            GameActivity.this.onBuySellUpdate();

            //TODO there needs to be a check in the inventory that if there is no inventory left, switch back to buy mode
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent myIntent = new Intent(getApplicationContext(), StartActivity.class);

        startActivityForResult(myIntent, 0);

        return false;

    }

    @Override
    public void onResume() {
    	super.onResume();
    	refreshDisplay();
    }
}
