package com.gamecook.fit.adaptors;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.gamecook.fit.collections.Locations;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 31, 2010
 * Time: 9:29:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationsAdaptor extends BaseAdapter {

    private Locations locations;

    public LocationsAdaptor(Locations locations) {
        this.locations = locations;
    }

    public int getCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
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
