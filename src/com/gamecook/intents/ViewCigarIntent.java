package com.gamecook.intents;

import android.content.Context;
import android.content.Intent;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 1, 2010
 * Time: 9:07:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewCigarIntent extends Intent {

    private String cigarName;

    public ViewCigarIntent(Intent o, String cigarName) {
        super(o);
        this.cigarName = cigarName;
    }

    public ViewCigarIntent(Context packageContext, Class<?> cls, String cigarName) {
        super(packageContext, cls);
        this.cigarName = cigarName;
    }


    public String getCigarName() {
        return cigarName;
    }

}