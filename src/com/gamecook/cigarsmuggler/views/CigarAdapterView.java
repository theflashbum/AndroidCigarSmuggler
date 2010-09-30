package com.gamecook.cigarsmuggler.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamecook.cigarsmuggler.R;
import com.gamecook.cigarsmuggler.core.CigarSmugglerGame;
import com.gamecook.cigarsmuggler.items.Cigar;
import com.gamecook.fit.collections.Inventory;
import com.gamecook.fit.managers.SingletonManager;
import com.gamecook.fit.util.MoneyToStringUtil;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Sep 9, 2010
 * Time: 8:44:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CigarAdapterView extends LinearLayout {

    private Cigar cigar;
    private Inventory inventory = ((CigarSmugglerGame) SingletonManager.getInstance().getClassReference(CigarSmugglerGame.class)).getInventory();
    private View mainView;

    public CigarAdapterView(Context context, Cigar cigar) {
        super(context);

        this.setOrientation(VERTICAL);
        this.setTag(cigar);

        mainView = inflate(context, R.layout.cigar_view, null);

        ((TextView) mainView.findViewById(R.id.CigarNameLabel)).setText(cigar.getName());
        ((TextView) mainView.findViewById(R.id.CostTotalText)).setText(MoneyToStringUtil.convertToString(cigar.getPrice(), true) + " / " + Integer.toString(cigar.getTotal()));
        ((TextView) mainView.findViewById(R.id.DescriptionText)).setText(cigar.getDescription());

        addView(mainView);

        HistoryGraphView graph = (HistoryGraphView) findViewById(R.id.HistoryGraphImage);

        graph.generateGraph(cigar.getPriceHistory());
    }

    public void isEnabled(Boolean value)
    {

        Float newAlpha = value ? 1.0f : 0.3f;

        Animation animation = new AlphaAnimation(1.0f, newAlpha);
        animation.setDuration(0);
        animation.setFillAfter(true);
        mainView.startAnimation(animation);
    }

}
