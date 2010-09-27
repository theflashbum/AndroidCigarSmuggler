package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gamecook.cigarsmuggler.R;
import com.gamecook.cigarsmuggler.core.CigarSmugglerGame;
import com.gamecook.cigarsmuggler.views.NumberPicker;
import com.gamecook.fit.commerce.Bank;
import com.gamecook.fit.commerce.Wallet;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.fit.util.MoneyToStringUtil;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 27, 2010
 * Time: 12:37:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Safe extends Activity implements View.OnClickListener, NumberPicker.OnChangedListener {

    private CigarSmugglerGame game = (CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class);
    private Bank bank = game.getBank();
    private Wallet wallet = game.getWallet();

    private Button depositButton;
    private Button withdrawButton;
    private Button repayLoanButton;
    private Button getLoanButton;
    private int currentMode;
    private static final int DEPOSIT = 0;
    private static final int WITHDRAW = 1;
    private static final int REPAY_LOAN = 2;
    private static final int GET_LOAN = 3;
    private String[] popupTitles;
    private int LOAN_VALUE = 100;
    private int currentPickerValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe);


        popupTitles = new String[]{"Deposit", "Withdraw", "Repay Load", "Get A Loan"};

        depositButton = (Button) findViewById(R.id.depositButton);
        depositButton.setOnClickListener(this);

        withdrawButton = (Button) findViewById(R.id.withdrawButton);
        withdrawButton.setOnClickListener(this);

        repayLoanButton = (Button) findViewById(R.id.repayLoanButton);
        repayLoanButton.setOnClickListener(this);

        getLoanButton = (Button) findViewById(R.id.getLoan);
        getLoanButton.setOnClickListener(this);

        refreshLabels();
    }


    private void refreshLabels() {

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


        if (bank.getLoan() > 0) {
            getLoanButton.setEnabled(false);
            getLoanButton.setTextColor(0x50ffffff);
            repayLoanButton.setEnabled(true);
            repayLoanButton.setTextColor(0xffffffff);
        } else {
            getLoanButton.setEnabled(true);
            getLoanButton.setTextColor(0xffffffff);
            repayLoanButton.setEnabled(false);
            repayLoanButton.setTextColor(0x50ffffff);
        }


    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.depositButton:
                createBankAlert(DEPOSIT);
                break;
            case R.id.withdrawButton:
                createBankAlert(WITHDRAW);
                break;
            case R.id.repayLoanButton:
                createBankAlert(REPAY_LOAN);
                break;
            case R.id.getLoan:
                createBankAlert(GET_LOAN);
                break;
        }
    }

    private void createBankAlert(int type) {
        //Context mContext = getApplicationContext();
        currentMode = type;
        String action = popupTitles[type];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(action)
                .setCancelable(false)
                .setPositiveButton(action, new BuyClickHandler())
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.bank_alert, null);

        alert.setView(layout);

        NumberPicker numberPicker = (NumberPicker) layout.findViewById(R.id.PurchaseNumberPicker);

        configureBankAlert(type, numberPicker);
        alert.show();

    }

    private void configureBankAlert(int type, NumberPicker numberPicker) {

        int min = 1;
        int max = 1;

        switch (type) {
            case DEPOSIT:
            case REPAY_LOAN:
                max = (int) bank.getLoan();
                if(max > wallet.getTotal())
                    max = (int) wallet.getTotal();
                break;
            case WITHDRAW:
                max = (int) bank.getSavings();
                break;
            case GET_LOAN:
                max = LOAN_VALUE;
                break;
        }

        numberPicker.setRange(min, max);
        numberPicker.setCurrent(min);

        numberPicker.setOnChangeListener(this);
    }

    public void onChanged(NumberPicker picker, int oldVal, int newVal) {
        currentPickerValue = newVal;
    }

    private class BuyClickHandler implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (currentMode) {

                case DEPOSIT:
                    wallet.subtract(currentPickerValue);
                    bank.depositIntoSavings(currentPickerValue);
                    break;
                case WITHDRAW:
                    wallet.add(currentPickerValue);
                    bank.withdrawFromSavings(currentPickerValue);
                    break;
                case REPAY_LOAN:
                    wallet.subtract(currentPickerValue);
                    bank.payOffLoan(currentPickerValue);
                    break;
                case GET_LOAN:
                    wallet.add(currentPickerValue);
                    bank.takeOutLoan(currentPickerValue);
                    break;
            }


            refreshLabels();
        }
    }
}
