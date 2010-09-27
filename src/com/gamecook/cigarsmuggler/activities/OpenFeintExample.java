package com.gamecook.cigarsmuggler.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.gamecook.cigarsmuggler.R;
import com.openfeint.api.OpenFeint;
import com.openfeint.api.OpenFeintDelegate;
import com.openfeint.api.OpenFeintSettings;
import com.openfeint.api.ui.Dashboard;
import com.openfeint.internal.OpenFeintInternal;
import com.openfeint.internal.ui.WebViewCache;

public class OpenFeintExample extends ListActivity {
	
	static final String[] BUTTONS = new String[] {
		"Dashboard",
		"Dashboard: Leaderboards",
		"Dashboard: Achievements",
		"Explore Leaderboards",
		"Explore Achievements",
		"Logout of Feint",
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        OpenFeintSettings settings = new OpenFeintSettings("Cigar Smuuggler", "LZZqBP7QbetME7CHZ7KWg", "6iF2ttagLSlSNLWPbIIcAUpDFOgVhHG1Q5x7rwdxY", "170892");
        
        OpenFeint.initialize(this, settings, new OpenFeintDelegate() { });
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu_item, BUTTONS));
        
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	// Show selected example activity
	        	Intent intent = null;
	        	
	        	if      (0 == position)		Dashboard.open();
	        	else if (1 == position) 	Dashboard.openLeaderboards();
	        	else if (2 == position) 	Dashboard.openAchievements();
	        	else if (3 == position)		intent = new Intent(OpenFeintExample.this, LeaderboardExplorer.class);
	        	else if (4 == position)		intent = new Intent(OpenFeintExample.this, AchievementExplorer.class);
	        	else if (5 == position)		{
	        		OpenFeintInternal.getInstance().logoutUser(null);
	        	} else 						throw new RuntimeException("Forcing app closure");
	        	
	        	if (intent != null) startActivity(intent);
	        }
	    });
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	OpenFeint.setCurrentActivity(this);
    	WebViewCache.resync();
    }
}
