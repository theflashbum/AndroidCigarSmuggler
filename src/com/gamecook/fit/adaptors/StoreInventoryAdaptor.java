package com.gamecook.fit.adaptors;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.gamecook.fit.collections.Store;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 31, 2010
 * Time: 9:18:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoreInventoryAdaptor extends BaseAdapter {

    private Store store;

    public StoreInventoryAdaptor(Store store) {

        this.store = store;
    }

    public int getCount() {
        return store.getTotalItems();
    }

    public Object getItem(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getItemId(int i) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
