package com.gamecook.cigarsmuggler.activities;

import android.app.Activity;
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
import com.openfeint.api.resource.Score;
import com.openfeint.api.ui.Dashboard;

import java.util.List;

public class ScoreExplorer extends ListActivity {

	private String mLeaderboardID;
	
	private abstract class Adapter {
		abstract public String toString();
		public void onClick() {};
	};
	
	private class PostAdapter extends Adapter {
		public String toString() { return "* Post new Score"; }
		public void onClick() {
        	Intent i = new Intent(ScoreExplorer.this, ScorePoster.class);
        	i.putExtra("leaderboard_id", mLeaderboardID);
        	ScoreExplorer.this.startActivityForResult(i, 0);
		}
	}
	
	private class OpenAdapter extends Adapter {
		public String toString() { return "* Open in Dashboard"; }
		public void onClick() {
			Dashboard.openLeaderboard(mLeaderboardID);
		}
	}
	
	private class ScoreAdapter extends Adapter {
		public Score mScore;
		public ScoreAdapter(Score score) {
			mScore = score;
		}
		public String toString() {
			String scoreText = new Long(mScore.score).toString(); 
			if (mScore.displayText != null && mScore.displayText.length() > 0) scoreText = mScore.displayText;
			String userText = "unknown";
			if (mScore.user != null && mScore.user.name != null) userText = mScore.user.name;
			return userText + " - " + scoreText;
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
        
        mLeaderboardID = this.getIntent().getExtras().getString("leaderboard_id");
        
        downloadScores();
    }
    
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode != Activity.RESULT_CANCELED) {
    		downloadScores();
    	}
    }
    
    public void downloadScores()
    {
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu_item, new String[] { "Loading..." }));

        new Leaderboard(mLeaderboardID).getScores(new Leaderboard.GetScoresCB() {
			@Override
			public void onSuccess(final List<Score> scores) {
				
				final Adapter adapted[] = new Adapter[(scores != null ? scores.size() : 0) + 2];
				int idx = 0;
				adapted[idx++] = new PostAdapter();
				adapted[idx++] = new OpenAdapter();
				
				if (scores != null) {
					for (Score s : scores) {
						adapted[idx++] = new ScoreAdapter(s);
					}
				}
				
		        setListAdapter(new ArrayAdapter<Adapter>(ScoreExplorer.this, R.layout.main_menu_item, adapted));
		        
		        ListView lv = getListView();
		        lv.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			        	adapted[position].onClick();
			        }
			    });

			}
			
			@Override public void onFailure(String exceptionMessage) {
		        setListAdapter(new ArrayAdapter<String>(ScoreExplorer.this, R.layout.main_menu_item, new String[] { "Error (" + exceptionMessage + ")" }));
			}
        });
    }

}
