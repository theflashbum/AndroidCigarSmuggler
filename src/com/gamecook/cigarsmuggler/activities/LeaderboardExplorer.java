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
import com.openfeint.api.resource.Leaderboard;

import java.util.List;

public class LeaderboardExplorer extends ListActivity {

	public class LeaderboardAdapter {
		public Leaderboard mLeaderboard;
		public LeaderboardAdapter(Leaderboard leaderboard) {
			mLeaderboard = leaderboard;
		}
		public String toString() {
			return mLeaderboard.name;
		}
	}
	
    @Override
    public void onResume() {
    	super.onResume();
    	OpenFeint.setCurrentActivity(this);
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu_item, new String[] { "Loading..." }));

        Leaderboard.list(new Leaderboard.ListCB() {
			@Override
			public void onSuccess(final List<Leaderboard> leaderboards) {
				if (null == leaderboards || leaderboards.size() == 0) {
			        setListAdapter(new ArrayAdapter<String>(LeaderboardExplorer.this, R.layout.main_menu_item, new String[] { "No Leaderboards." }));
			        return;
				}
				
				LeaderboardAdapter adapted[] = new LeaderboardAdapter[leaderboards.size()];
				for(int i=0; i<leaderboards.size(); ++i) {
					adapted[i] = new LeaderboardAdapter(leaderboards.get(i));
				}
		        setListAdapter(new ArrayAdapter<LeaderboardAdapter>(LeaderboardExplorer.this, R.layout.main_menu_item, adapted));
		        
		        ListView lv = getListView();
		        lv.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			        	Leaderboard l = leaderboards.get(position);
			        	Intent i = new Intent(LeaderboardExplorer.this, ScoreExplorer.class);
			        	i.putExtra("leaderboard_id", l.resourceID());
			        	startActivity(i);
			        }
			    });
			}
			
			@Override public void onFailure(String exceptionMessage) {
		        setListAdapter(new ArrayAdapter<String>(LeaderboardExplorer.this, R.layout.main_menu_item, new String[] { "Error (" + exceptionMessage + ")" }));
			}
        });
    }
}
