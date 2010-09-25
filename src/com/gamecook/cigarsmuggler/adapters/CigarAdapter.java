package com.gamecook.cigarsmuggler.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.gamecook.cigarsmuggler.views.CigarAdapterView;
import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.collections.Store;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 9, 2010
 * Time: 8:33:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CigarAdapter extends BaseAdapter {

    private Context context;
    private Store store;
    private Inventory inventory;
    private Double cash;
    private String buySellMode;

    public CigarAdapter(Context context, Store store, Inventory inventory, Double cash, String buySellMode) {

        this.context = context;
        this.store = store;
        this.inventory = inventory;
        this.cash = cash;
        this.buySellMode = buySellMode;
    }

    public int getCount() {
        return store.getTotalItems();
    }

    public Object getItem(int i) {
        return store.getItemByID(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        Cigar cigar = (Cigar) getItem(i);

        CigarAdapterView cigarView = new CigarAdapterView(context, cigar);

        cigarView.isEnabled(isEnabled(i));

        return cigarView;
    }

    @Override
    public boolean isEnabled(int position) {

        Cigar cigar = (Cigar) getItem(position);

        if (buySellMode == "buy") {

            //Test to see if there is room in inventory for item
            if(inventory.getTotalLeft() < 1)
                return false;

            if (cigar.getPrice() < cash)
                return true;
            else
                return false;

        } else {
            if (cigar.getTotal() > 0)
                return true;
            else
                return false;

        }
    }
}
